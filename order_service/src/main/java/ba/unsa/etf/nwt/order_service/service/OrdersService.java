package ba.unsa.etf.nwt.order_service.service;

import ba.unsa.etf.nwt.order_service.DTO.OrdersDTO;
import ba.unsa.etf.nwt.order_service.exception.NotFoundException;
import ba.unsa.etf.nwt.order_service.model.Date;
import ba.unsa.etf.nwt.order_service.model.Orders;
import ba.unsa.etf.nwt.order_service.model.State;
import ba.unsa.etf.nwt.order_service.repository.DateRepository;
import ba.unsa.etf.nwt.order_service.repository.OrderRepository;
import ba.unsa.etf.nwt.order_service.repository.StateRepository;
import ba.unsa.etf.nwt.order_service.response.GetItemsResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
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

    @Autowired
    private RestTemplate restTemplate;

    private final DiscoveryClient discoveryClient;

    public OrdersService(RestTemplate restTemplate, DiscoveryClient discoveryClient, OrderRepository orderRepository,DateRepository dateRepository,StateRepository stateRepository) {
        this.orderRepository = orderRepository;
        this.dateRepository = dateRepository;
        this.stateRepository = stateRepository;
        this.restTemplate = restTemplate;
        this.discoveryClient = discoveryClient;
    }

    public List<OrdersDTO> getAllOrders(){
        return orderRepository.findAll().stream().map(orders -> mapToDTO(orders, new OrdersDTO())).collect(Collectors.toList());
    }

    public OrdersDTO getOrderById(Integer id){
        return orderRepository.findById(id).map(orders->mapToDTO(orders,new OrdersDTO())).orElseThrow(()-> new NotFoundException(id,"order"));
    }

    public Integer getDateIdForOrder(Integer id){
        return orderRepository.getDateIdForOrder(id);
    }

    public Integer addOrder(OrdersDTO ordersDTO){
        Orders orders = new Orders();
        Integer orderId = null;

        ServiceInstance serviceInstanceRecipe = discoveryClient.getInstances("user-service").get(0);
        String resourceURL = serviceInstanceRecipe.getUri() + "/api/user/";
        boolean userExist = false;
        try{
            ResponseEntity<String> response= restTemplate.getForEntity(resourceURL+ordersDTO.getUserId(), String.class);
            if (response.getStatusCode().equals(HttpStatus.OK)) {
                userExist = true;
            }
        }catch(Exception e) {
            throw new NotFoundException(ordersDTO.getUserId(),"user");
        }
        if(userExist){
            mapToEntity(ordersDTO,orders);
            orderId = orderRepository.save(orders).getId();
        }
        return orderId;
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

    public List<GetItemsResponse> getItemsFromCart(Integer id){
        List<GetItemsResponse> itemsspecifications = new ArrayList<>();
        ServiceInstance serviceInstanceRecipe = discoveryClient.getInstances("item_cart-service").get(0);
        String resourceURL = serviceInstanceRecipe.getUri() + "/api/item_cart/order/";
        ResponseEntity<String> response = restTemplate.getForEntity(resourceURL + id,String.class);
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode root = mapper.readTree(response.getBody());
            for (int i = 0; i < root.size(); i++) {
                Integer itemId = root.get(i).path("itemId").asInt();
                String color = root.get(i).path("color").asText();
                String height = root.get(i).path("height").asText();
                String width = root.get(i).path("width").asText();
                String depth = root.get(i).path("depth").asText();
                String material = root.get(i).path("material").asText();
                GetItemsResponse getItemsResponse = new GetItemsResponse(itemId,color,height,width,depth,material);
                itemsspecifications.add(getItemsResponse);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return itemsspecifications;
    }
    public Integer getDaysForManufacturing(List<Integer> items){
        int numOfDays = 0;
        for(Integer itemId: items){
            ServiceInstance serviceInstance = discoveryClient.getInstances("item-service").get(0);
            String resourceURL = serviceInstance.getUri() + "/api/item/" + itemId + "/days";
            ResponseEntity<Integer> response = restTemplate.getForEntity(resourceURL,Integer.class);
            Integer days = response.getBody();
            if(days != null && days > numOfDays) numOfDays = days;
        }
        return numOfDays;
    }
    private OrdersDTO mapToDTO(final Orders orders, final OrdersDTO ordersDTO) {
        ordersDTO.setId(orders.getId());
        ordersDTO.setUserId(orders.getUserId());
        ordersDTO.setDateId(orders.getDate().getId());
        ordersDTO.setStateId(orders.getState().getId());
        return ordersDTO;
    }

    private void mapToEntity(final OrdersDTO ordersDTO, final Orders orders) {
        orders.setUserId(ordersDTO.getUserId());
        State state = stateRepository.findById(ordersDTO.getStateId()).orElseThrow(()-> new NotFoundException(ordersDTO.getStateId(),"state"));
        Date date = dateRepository.findById(ordersDTO.getDateId()).orElseThrow(()-> new NotFoundException(ordersDTO.getDateId(), "date"));
        orders.setDate(date);
        orders.setState(state);
    }

}
