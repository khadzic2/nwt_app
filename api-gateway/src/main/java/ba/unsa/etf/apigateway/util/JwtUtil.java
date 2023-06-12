package ba.unsa.etf.apigateway.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    public static final String SECRET="472B4B6150645367566B5970337336763979244226452948404D635165546857";

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtil.class);
    public void validateToken(final String token){
        try {
            Jwts
                    .parserBuilder()
                    .setSigningKey(getSignInKey())
                    .build()
                    .parseClaimsJws(token);
        }catch (ExpiredJwtException ex) {
        LOGGER.error("JWT expired", ex.getMessage());
    } catch (IllegalArgumentException ex) {
        LOGGER.error("Token is null, empty or only whitespace", ex.getMessage());
    } catch (MalformedJwtException ex) {
        LOGGER.error("JWT is invalid", ex);
    } catch (UnsupportedJwtException ex) {
            LOGGER.error("JWT is not supported", ex);
        }
    }

    public Claims extractClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
