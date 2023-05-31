package ba.unsa.etf.nwt.order_service.controller;

import ba.unsa.etf.nwt.order_service.DTO.OrdersDTO;
import ba.unsa.etf.nwt.order_service.service.DateService;
import ba.unsa.etf.nwt.order_service.service.OrdersService;
import ba.unsa.etf.nwt.order_service.service.StateService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(value = "/api/order", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {
    private final OrdersService ordersService;
    private final DateService dateService;
    private final StateService stateService;

    public OrderController(OrdersService ordersService, DateService dateService, StateService stateService) {
        this.ordersService = ordersService;
        this.dateService = dateService;
        this.stateService = stateService;
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrdersDTO>> all() {
        return new ResponseEntity<>(ordersService.getAllOrders(), HttpStatus.OK);
    }

    @PostMapping("/orders")
    public ResponseEntity<Integer> newOrders(@RequestBody @Valid OrdersDTO newOrders) {
        return new ResponseEntity<>(ordersService.addOrder(newOrders),HttpStatus.CREATED);
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<OrdersDTO> one(@PathVariable Integer id) {
        return new ResponseEntity<>(ordersService.getOrderById(id),HttpStatus.OK);
    }

    @PutMapping("/orders/{id}")
    public ResponseEntity<String> replaceOrders(@RequestBody @Valid OrdersDTO newOrders, @PathVariable Integer id) {
        ordersService.updateOrder(newOrders,id);
        return new ResponseEntity<>("Successfully updated!",HttpStatus.OK);
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<String> deleteOrders(@PathVariable Integer id) {
        ordersService.deleteOrder(id);
        return new ResponseEntity<>("Successfully deleted!",HttpStatus.OK);
    }

    @GetMapping("/orders/user/{id}")
    public ResponseEntity<List<OrdersDTO>> getOrdersFromUser(@PathVariable Integer id){
        return ResponseEntity.ok(ordersService.getOrdersFromUser(id));
    }
}
