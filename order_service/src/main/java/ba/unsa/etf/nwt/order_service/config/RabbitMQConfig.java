package ba.unsa.etf.nwt.order_service.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Configuration
    public static class OrderDeleteQueueConfig {

        public static final String QUEUE_NAME = "delete-order-queue";
        public static final String EXCHANGE_NAME = "delete-order-exchange";
        public static final String ROUTING_KEY = "delete-order-routing-key";

        @Bean
        public Queue deleteOrderQueue() {
            return new Queue(QUEUE_NAME);
        }

        @Bean
        public DirectExchange deleteOrderExchange() {
            return new DirectExchange(EXCHANGE_NAME);
        }

        @Bean
        public Binding deleteOrderBinding(Queue deleteOrderQueue, DirectExchange deleteOrderExchange) {
            return BindingBuilder
                    .bind(deleteOrderQueue)
                    .to(deleteOrderExchange)
                    .with(ROUTING_KEY);
        }
    }

    @Bean
    public MessageConverter messageConverter() {
        ObjectMapper mapper = new ObjectMapper()
                .findAndRegisterModules()
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return new Jackson2JsonMessageConverter(mapper);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory factory, MessageConverter converter) {
        RabbitTemplate template = new RabbitTemplate(factory);
        template.setMessageConverter(converter);
        return template;
    }
}
