package ba.unsa.etf.nwt.itemcart_service.messaging;

import ba.unsa.etf.nwt.itemcart_service.DTO.OrdersDTO;
import ba.unsa.etf.nwt.itemcart_service.config.RabbitMQConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderDeletePublisher implements Publisher<OrdersDTO> {

    private final RabbitTemplate template;

    @Override
    public void send(OrdersDTO order) {
        this.template.convertAndSend(
                RabbitMQConfig.RevertOrderDeleteQueueConfig.EXCHANGE_NAME,
                RabbitMQConfig.RevertOrderDeleteQueueConfig.ROUTING_KEY,
                order
        );
    }
}
