package ba.unsa.etf.apigateway.filter;

import ba.unsa.etf.apigateway.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@Slf4j
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private final WebClient.Builder webClientBuilder;

    public AuthenticationFilter(WebClient.Builder webClientBuilder) {
        super(Config.class);
        this.webClientBuilder=webClientBuilder;
    }

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RouteValidator routeValidator;

    
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public GatewayFilter apply(Config config){
        log.info("pozvao se api gateway");
        return ((exchange, chain)->{
            if(routeValidator.isSecured.test(exchange.getRequest())){
                //header contains token or not
                if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                    throw new RuntimeException("Missing authorization header [line 35]");
                }
                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if(authHeader != null && authHeader.startsWith("Bearer ")){
                    authHeader = authHeader.substring(7);
                }
                final String token = authHeader;
                try{

                    return WebClient.create("http://localhost:8089")
                            .get()
                            .uri("/api/auth/validate?token=" + token)
                            //.header(SecurityConstants.HEADER, bearerToken) // ovdje ne prepoznaje SecurityConstants i nema in na medium clanku
                            .header(HttpHeaders.AUTHORIZATION, authHeader)
                            .retrieve()
                            .onStatus(
                                    status -> status.is4xxClientError() || status.is5xxServerError(),
                                    response -> {
                                        log.error("Error status: " + response.statusCode());
                                        // Handle the error case here
                                        return response.createException();
                                    }
                            )
                           // .bodyToMono(ResponseEntity.class)
                            .bodyToMono(String.class)
                            .map(response -> {
                                log.info("map*************************");


                                if (response.equals("Token is valid")) {
                                    log.info("Token is valid");

                                    Claims claims = jwtUtil.extractClaimsFromToken(token);
                                    String username = claims.getSubject();
                                    String role = (String) claims.get("role");
                                    // Continue with the filter chain
                                    exchange.mutate()
                                            .request(request -> request.headers(headers -> headers.add("X-User-Role", role)))
                                            .build();
                                    log.info("role: " + role);
                                    log.info("exchange req: " + exchange.getRequest().getURI());
                                    log.info("exchange req headers: " + exchange.getRequest().getHeaders());
                                    log.info("exchange req path: " + exchange.getRequest().getPath());
                                    return exchange;
                                } else {
                                    log.error("Error: " + response);
                                    // Handle the error case here
                                    // return Mono.error(new RuntimeException("Received non-success status: " + statusCode));
                                }
                                return exchange;
                            })
                            .flatMap(chain::filter)
                            .onErrorResume(error -> {
                                log.info("Error Happened");
                                log.info("error stack trace: " +error);
                                log.info("error message: " + error.getMessage());

                                HttpStatusCode errorCode = null;
                                String errorMsg = "";
                                if (error instanceof WebClientResponseException) {
                                    WebClientResponseException webClientException = (WebClientResponseException) error;
                                    errorCode = webClientException.getStatusCode();
                                    errorMsg = webClientException.getStatusText();
                                    log.info("Error msg: " + errorMsg);
                                    log.info("Error headers: " + ((WebClientResponseException) error).getHeaders());
                                    log.info("Error response body: " + ((WebClientResponseException) error).getResponseBodyAsString());


                                } else {
                                    errorCode = HttpStatus.BAD_GATEWAY;
                                    errorMsg = HttpStatus.BAD_GATEWAY.getReasonPhrase();
                                }
//                            AuthorizationFilter.AUTH_FAILED_CODE
                                return onError(exchange, String.valueOf(errorCode.value()) ,errorMsg, "JWT Authentication Failed", errorCode);
                            });


                }catch(Exception e){
                    System.out.println("invalid access");
                    throw new RuntimeException(e.getMessage() + "   unauthorized access to application");
                }
            }

            log.info("nije secured ruta -> poziva se chain.filter(exchange)");
            log.info(chain.toString());
            log.info(exchange.toString());
            return chain.filter(exchange);
        });
    }

    private Mono<Void> onError(ServerWebExchange exchange, String errCode, String err, String errDetails, HttpStatusCode httpStatus) {
        DataBufferFactory dataBufferFactory = exchange.getResponse().bufferFactory();
//        ObjectMapper objMapper = new ObjectMapper();
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }

    @NoArgsConstructor
    public static class Config{

    }

}
