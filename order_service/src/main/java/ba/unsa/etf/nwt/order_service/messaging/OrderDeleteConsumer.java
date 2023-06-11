package ba.unsa.etf.nwt.order_service.messaging;

import ba.unsa.etf.nwt.order_service.repository.OrderRepository;
import ba.unsa.etf.nwt.order_service.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class OrderDeleteConsumer implements Consumer<Integer>{
    private final OrdersService ordersService;
    private final OrderRepository orderRepository;
    @RabbitListener(queues = "revert-order-delete-queue")
    public void receive(Integer orderId){
        orderRepository.findById(orderId).ifPresent(orders -> deleteOrder(orderId));
    }

    @Transactional
    public void deleteOrder(Integer orderId){ordersService.deleteOrder(orderId);}
}
