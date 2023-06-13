package ba.unsa.etf.nwt.order_service.service;

import ba.unsa.etf.nwt.order_service.DTO.OrderItemsDTO;
import ba.unsa.etf.nwt.order_service.exception.NotFoundException;
import ba.unsa.etf.nwt.order_service.model.OrderItems;
import ba.unsa.etf.nwt.order_service.model.Orders;
import ba.unsa.etf.nwt.order_service.repository.OrderItemsRepository;
import ba.unsa.etf.nwt.order_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemsService {
    @Autowired
    private final OrderItemsRepository orderItemsRepository;
    @Autowired
    private final OrderRepository orderRepository;

    public OrderItemsService(OrderItemsRepository orderItemsRepository,OrderRepository orderRepository) {
        this.orderItemsRepository = orderItemsRepository;
        this.orderRepository = orderRepository;
    }

    public List<OrderItemsDTO> getAllOrderItems(){
        return orderItemsRepository.findAll().stream().map(orderItems -> mapToDTO(orderItems, new OrderItemsDTO())).collect(Collectors.toList());
    }

    public OrderItemsDTO getOrderItemsById(Integer id){
        return orderItemsRepository.findById(id).map(orderItems -> mapToDTO(orderItems, new OrderItemsDTO())).orElseThrow(()-> new NotFoundException(id,"order items"));
    }

    public Integer addOrderItems(OrderItemsDTO orderItemsDTO){
        OrderItems orderItems = new OrderItems();
        mapToEntity(orderItemsDTO,orderItems);
        return orderItemsRepository.save(orderItems).getId();
    }

    public void deleteOrderItems(Integer id){
        orderItemsRepository.deleteById(id);
    }

    public void deleteAll(){
        orderItemsRepository.deleteAll();
    }

    public List<Integer> getItemsFromOrder(Integer id){
        return orderItemsRepository.getItemsFromOrder(id);
    }

    public boolean orderExist(Integer itemId){
        return orderItemsRepository.countOrdersForItem(itemId) != 0;
    }

    public void deleteOrder(Integer orderId){
        orderItemsRepository.deleteOrder(orderId);
    }

    private OrderItemsDTO mapToDTO(final OrderItems orderItems, final OrderItemsDTO orderItemsDTO) {
        orderItemsDTO.setId(orderItems.getId());
        orderItemsDTO.setItemId(orderItems.getItemId());
        orderItemsDTO.setOrderId(orderItems.getOrders().getId());
        return orderItemsDTO;
    }

    private void mapToEntity(final OrderItemsDTO orderItemsDTO, final OrderItems orderItems) {
        orderItems.setItemId(orderItemsDTO.getItemId());
        Orders orders = orderRepository.findById(orderItemsDTO.getOrderId()).orElseThrow(()->new NotFoundException(orderItemsDTO.getOrderId(),"order"));
        orderItems.setOrders(orders);
    }
}
