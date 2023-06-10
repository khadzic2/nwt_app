package ba.unsa.etf.nwt.user_service.security_serivce;

import ba.unsa.etf.nwt.user_service.domain.Permission;
import ba.unsa.etf.nwt.user_service.domain.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JwtService {
    public static final String SECRET="472B4B6150645367566B5970337336763979244226452948404D635165546857";
    
    public void validateToken(final String token){
        Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token);
    }

    public String generateToken(String username, Role role){
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role.name());
        Set<String> permissions = role.getPermissions().stream()
                .map(Permission::getPermission)
                .collect(Collectors.toSet());

        claims.put("permissions", permissions);
        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String username){
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 10000*60*30))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
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

    public boolean isTokenValid(String token, String username){
        String token_username = extractUsername(token);
        return username.equals(token_username) && !isExpired(token);
    }

    public boolean isExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

}
