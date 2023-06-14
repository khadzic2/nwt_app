package ba.unsa.etf.nwt.order_service.controller;

import ba.unsa.etf.nwt.order_service.DTO.OrdersDTO;
import ba.unsa.etf.nwt.order_service.messaging.OrderDeletePublisher;
import ba.unsa.etf.nwt.order_service.response.GetItemsResponse;
import ba.unsa.etf.nwt.order_service.service.DateService;
import ba.unsa.etf.nwt.order_service.service.OrderItemsService;
import ba.unsa.etf.nwt.order_service.service.OrdersService;
import ba.unsa.etf.nwt.order_service.service.StateService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(value = "/api/order", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {
    private final OrdersService ordersService;
    private final DateService dateService;
    private final OrderItemsService orderItemsService;

    private final OrderDeletePublisher publisher;

    public OrderController(OrdersService ordersService, DateService dateService, StateService stateService, OrderItemsService orderItemsService, OrderDeletePublisher publisher) {
        this.ordersService = ordersService;
        this.dateService = dateService;
        this.orderItemsService = orderItemsService;
        this.publisher = publisher;
    }
    //@PreAuthorize("hasAnyAuthority('customer:read', 'admin:read')")
    @GetMapping("/orders")
    public ResponseEntity<List<OrdersDTO>> all() {
        return new ResponseEntity<>(ordersService.getAllOrders(), HttpStatus.OK);
    }
    //@PreAuthorize("hasAuthority('customer:create')")
    @PostMapping("/orders")
    public ResponseEntity<Integer> newOrders(@RequestBody @Valid OrdersDTO newOrders) {
        return new ResponseEntity<>(ordersService.addOrder(newOrders),HttpStatus.CREATED);
    }
    //@PreAuthorize("hasAuthority('customer:read')")
    @GetMapping("/orders/{id}")
    public ResponseEntity<OrdersDTO> one(@PathVariable Integer id) {
        return new ResponseEntity<>(ordersService.getOrderById(id),HttpStatus.OK);
    }
    //@PreAuthorize("hasAuthority('customer:update')")
    @PutMapping("/orders/{id}")
    public ResponseEntity<String> replaceOrders(@RequestBody @Valid OrdersDTO newOrders, @PathVariable Integer id) {
        ordersService.updateOrder(newOrders,id);
        return new ResponseEntity<>("Successfully updated!",HttpStatus.OK);
    }
    //@PreAuthorize("hasAuthority('customer:delete')")
    @DeleteMapping("/orders/{id}")
    public ResponseEntity<String> deleteOrders(@PathVariable Integer id) {
        //orderItemsService.deleteOrder(id);
        //Integer idDate = ordersService.getDateIdForOrder(id);
        ordersService.deleteOrder(id);
        //dateService.deleteDate(idDate);
        //publisher.send(id);
        return new ResponseEntity<>("Successfully deleted!",HttpStatus.OK);
    }

    @GetMapping("/orders/user/{id}")
    public ResponseEntity<List<OrdersDTO>> getOrdersFromUser(@PathVariable Integer id){
        return ResponseEntity.ok(ordersService.getOrdersFromUser(id));
    }

    @GetMapping("/{id}/items_cart")
    public ResponseEntity<List<GetItemsResponse>> getItemsFromCart(@PathVariable Integer id){
        return ResponseEntity.ok(ordersService.getItemsFromCart(id));
    }

    @GetMapping("/{itemId}/orderExist")
    public ResponseEntity<Boolean> orderExist(@PathVariable Integer itemId){
        return new ResponseEntity<>(orderItemsService.orderExist(itemId),HttpStatus.OK);
    }

    @GetMapping("/{orderId}/items/days")
    public ResponseEntity<Integer> getDaysForManufacturing(@PathVariable Integer orderId){
        List<Integer> items = orderItemsService.getItemsFromOrder(orderId);
        return new ResponseEntity<>(ordersService.getDaysForManufacturing(items),HttpStatus.OK);
    }

}
