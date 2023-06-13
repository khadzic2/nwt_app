package ba.unsa.etf.nwt.user_service.logging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class GRPCInterceptorConfig implements WebMvcConfigurer {
    @Autowired
    GRPCInterceptor grpcInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(grpcInterceptor);
    }
}