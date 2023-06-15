package ba.unsa.etf.nwt.user_service.security_serivce;

import ba.unsa.etf.nwt.user_service.service.UserService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
//@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired

    private  JwtService jwtService;

    @Autowired
    private  UserService userService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        try {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String jwt;
        final String username;

        if(isRouteSecured(request)) {
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                logger.info("authHeader je null ili ne pocinje sa bearer ");
                filterChain.doFilter(request, response);
                return;
            }
        }
        else{
            filterChain.doFilter(request, response);
            return;
        }
        jwt = authHeader.substring(7);

        username =jwtService.extractUsername(jwt);
        if(username!=null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = this.userService.loadUserByUsername(username);
            if(jwtService.isTokenValid(jwt, userDetails.getUsername())){
                Claims claims = jwtService.extractClaimsFromToken(jwt);
                String jwt_username = claims.getSubject();
                String role = (String) claims.get("role");
                Set<SimpleGrantedAuthority> simpleGrantedAuthorities = new HashSet<>();
                simpleGrantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+role));

                for (GrantedAuthority authority : userDetails.getAuthorities()) {
                    simpleGrantedAuthorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
                }
                logger.info("userDetails.getAuthorities: " + userDetails.getAuthorities());
                logger.info("simple granthed authorities: " + simpleGrantedAuthorities);

                Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, simpleGrantedAuthorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

            filterChain.doFilter(request, response);
        }catch (AccessDeniedException e){
            logger.info("e poruka: " + e.getMessage());
        }
    }

    private boolean isRouteSecured(HttpServletRequest request) {
        List<RequestMatcher> securedMatchers = Arrays.asList(
                new AntPathRequestMatcher("/api/auth/register"),
                new AntPathRequestMatcher("/api/auth/validate"),
                new AntPathRequestMatcher("/api/auth/token"),
                new AntPathRequestMatcher("/api/auth/login"),
                new AntPathRequestMatcher("/api/users/**")
        );

        for (RequestMatcher matcher : securedMatchers) {
            if (matcher.matches(request)) {
                return false;
            }
        }

        return true;
    }


}
