package ba.unsa.etf.nwt.order_service.messaging;

import ba.unsa.etf.nwt.order_service.config.RabbitMQConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderDeletePublisher implements Publisher<Integer> {

    private final RabbitTemplate template;

    @Override
    public void send(Integer idOrder) {
        this.template.convertAndSend(
                RabbitMQConfig.OrderDeleteQueueConfig.EXCHANGE_NAME,
                RabbitMQConfig.OrderDeleteQueueConfig.ROUTING_KEY,
                idOrder
        );
    }
}
