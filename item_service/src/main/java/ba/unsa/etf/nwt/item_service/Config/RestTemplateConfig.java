package ba.unsa.etf.nwt.item_service.Config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    @LoadBalanced
    @Bean
    RestTemplate loadBalanced () {
        return new RestTemplate();
    }

    @Primary
    @Bean
    RestTemplate restTemplate () {
        return new RestTemplate();
    }
}