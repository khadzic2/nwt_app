package ba.unsa.etf.nwt.order_service.messaging;

import ba.unsa.etf.nwt.order_service.DTO.OrdersDTO;
import ba.unsa.etf.nwt.order_service.repository.OrderRepository;
import ba.unsa.etf.nwt.order_service.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderDeleteConsumer implements Consumer<OrdersDTO>{
    private final OrderRepository orderRepository;
    @RabbitListener(queues = "revert-order-delete-queue")
    public void receive(OrdersDTO order){
        orderRepository.findById(order.getId()).ifPresent(orders -> {
            orders.setDeleted(false);
            orderRepository.save(orders);
        });
    }
}
