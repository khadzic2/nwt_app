package ba.unsa.etf.nwt.order_service.security;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    public static final String SECRET="472B4B6150645367566B5970337336763979244226452948404D635165546857";

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String jwt;

        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            logger.info("authHeader je null ili ne pocinje sa bearer ");
            filterChain.doFilter(request,response);
            return;
        }
        jwt = authHeader.substring(7);


        if(SecurityContextHolder.getContext().getAuthentication() == null){
            Claims claims = extractClaimsFromToken(jwt);
            String username = extractUsername(jwt);
            String role = (String) claims.get("role");
            ArrayList<String> permissions = (ArrayList<String>) claims.get("permissions");
            Set<SimpleGrantedAuthority> simpleGrantedAuthorities = new HashSet<>();
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+role));
            simpleGrantedAuthorities.addAll(permissions.stream()
                    .map(permission -> new SimpleGrantedAuthority(permission))
                    .collect(Collectors.toList()));


            Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, simpleGrantedAuthorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public Claims extractClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public<T> T extractClaim(String token, Function<Claims, T> resolver){
        final Claims claims = extractClaimsFromToken(token);
        return resolver.apply(claims);
    }

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

}