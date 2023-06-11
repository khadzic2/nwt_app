package ba.unsa.etf.nwt.itemcart_service.messaging;

import ba.unsa.etf.nwt.itemcart_service.service.ItemCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class OrderDeleteConsumer implements Consumer<Integer>{
    private final ItemCartService itemCartService;

    private final OrderDeletePublisher publisher;

    @RabbitListener(queues = "delete-order-queue")
    public void receive(Integer orderId){
        try {
            deleteOrder(orderId);
        }catch (Exception e){
            publisher.send(orderId);
        }
    }

    @Transactional
    public void deleteOrder(Integer orderId){itemCartService.deleteByOrderId(orderId);}
}
