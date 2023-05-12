package ba.unsa.etf.nwt.order_service.service;

import ba.unsa.etf.nwt.order_service.exception.NotFoundException;
import ba.unsa.etf.nwt.order_service.model.Orders;
import ba.unsa.etf.nwt.order_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService {
    @Autowired
    private final OrderRepository orderRepository;

    public OrdersService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Orders> getAllOrders(){
        return orderRepository.findAll();
    }

    public Orders getOrderById(Integer id){
        return orderRepository.findById(id).orElseThrow(()-> new NotFoundException(id,"order"));
    }

    public Orders addOrder(Orders orders){
        return orderRepository.save(orders);
    }

    public void deleteOrder(Integer id){
        orderRepository.deleteById(id);
    }

    public void deleteAll(){
        orderRepository.deleteAll();
    }

    public Orders updateOrder(Orders newOrder, Integer id){
        Orders oldOrder = getOrderById(id);
        oldOrder.setItemId(newOrder.getItemId());
        oldOrder.setState(newOrder.getState());
        oldOrder.setDate(newOrder.getDate());
        oldOrder.setUser(newOrder.getUser());
        orderRepository.save(oldOrder);
        return oldOrder;
    }
}
