package ba.unsa.etf.nwt.user_service.logging;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Base64;

@Component
public class GRPCInterceptor implements HandlerInterceptor {
    @Autowired
    private GRPCService grpcService;

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) throws Exception {

        String responseType = "";
        System.out.println("Status " + response.getStatus());
        if(response.getStatus() == 200 || response.getStatus() == 201){
            responseType = "OK";
        }
        else if(response.getStatus() == 404){
            responseType = "ERROR - ResourceNotFound";
        }
        else if(response.getStatus() == 401){
            responseType = "ERROR - Unauthorized";
        }
        else if(response.getStatus() == 403){
            responseType = "ERROR - AccessDenied (Forbidden)";
        }
        else {
            responseType = "ERROR - WrongInput/Validation";
        }
        String username = "guest";
//        try{
//            String authHeader=request.getHeader("Authorization");
//            String token = authHeader.substring(7);
//            final String SECRET = Base64.getEncoder().encodeToString("JwtSecretKey".getBytes());
//            Claims claims = Jwts.parser()
//                    .setSigningKey(SECRET)
//                    .parseClaimsJws(token)
//                    .getBody();
//            username = claims.getIssuer();
//        } catch ( Exception e){
//            username="guest";
//        }
        grpcService.save(request.getMethod(), request.getRequestURI(), responseType, username);
    }
}
