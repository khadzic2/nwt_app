package ba.unsa.etf.nwt.order_service.service;

import ba.unsa.etf.nwt.order_service.DTO.OrdersDTO;
import ba.unsa.etf.nwt.order_service.exception.NotFoundException;
import ba.unsa.etf.nwt.order_service.model.Date;
import ba.unsa.etf.nwt.order_service.model.Orders;
import ba.unsa.etf.nwt.order_service.model.State;
import ba.unsa.etf.nwt.order_service.repository.DateRepository;
import ba.unsa.etf.nwt.order_service.repository.OrderRepository;
import ba.unsa.etf.nwt.order_service.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdersService {
    @Autowired
    private final OrderRepository orderRepository;

    @Autowired
    private final DateRepository dateRepository;

    @Autowired
    private final StateRepository stateRepository;
    public OrdersService(OrderRepository orderRepository,DateRepository dateRepository,StateRepository stateRepository) {
        this.orderRepository = orderRepository;
        this.dateRepository = dateRepository;
        this.stateRepository = stateRepository;
    }

    public List<OrdersDTO> getAllOrders(){
        return orderRepository.findAll().stream().map(orders -> mapToDTO(orders, new OrdersDTO())).collect(Collectors.toList());
    }

    public OrdersDTO getOrderById(Integer id){
        return orderRepository.findById(id).map(orders->mapToDTO(orders,new OrdersDTO())).orElseThrow(()-> new NotFoundException(id,"order"));
    }

    public Integer addOrder(OrdersDTO ordersDTO){
        Orders orders = new Orders();
        mapToEntity(ordersDTO,orders);
        return orderRepository.save(orders).getId();
    }

    public void deleteOrder(Integer id){
        orderRepository.deleteById(id);
    }

    public void deleteAll(){
        orderRepository.deleteAll();
    }

    public void updateOrder(OrdersDTO newOrder, Integer id){
        Orders oldOrder = orderRepository.findById(id).orElseThrow(()->new NotFoundException(id,"order"));

        mapToEntity(newOrder,oldOrder);
        orderRepository.save(oldOrder);
    }

    public List<OrdersDTO> getOrdersFromUser(Integer userId){
        return orderRepository.getOrdersFromUser(userId).stream().map(orders -> mapToDTO(orders, new OrdersDTO())).collect(Collectors.toList());
    }

    private OrdersDTO mapToDTO(final Orders orders, final OrdersDTO ordersDTO) {
        ordersDTO.setId(orders.getId());
        ordersDTO.setUserId(orders.getUserId());
        ordersDTO.setItemId(orders.getItemId());
        ordersDTO.setDateId(orders.getDate().getId());
        ordersDTO.setStateId(orders.getState().getId());
        return ordersDTO;
    }

    private void mapToEntity(final OrdersDTO ordersDTO, final Orders orders) {
        orders.setUserId(ordersDTO.getUserId());
        orders.setItemId(ordersDTO.getItemId());
        State state = stateRepository.findById(ordersDTO.getStateId()).orElseThrow(()-> new NotFoundException(ordersDTO.getStateId(),"state"));
        Date date = dateRepository.findById(ordersDTO.getDateId()).orElseThrow(()-> new NotFoundException(ordersDTO.getDateId(), "date"));
        orders.setDate(date);
        orders.setState(state);
    }
}
