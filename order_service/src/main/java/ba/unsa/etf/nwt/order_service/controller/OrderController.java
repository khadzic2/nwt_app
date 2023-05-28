package ba.unsa.etf.nwt.order_service.controller;

import ba.unsa.etf.nwt.order_service.model.Orders;
import ba.unsa.etf.nwt.order_service.service.OrdersService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class OrderController {
    private final OrdersService ordersService;

    OrderController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Orders>> all() {
        return new ResponseEntity<>(ordersService.getAllOrders(), HttpStatus.OK);
    }

    @PostMapping("/orders")
    public ResponseEntity<Orders> newOrders(@RequestBody @Valid Orders newOrders) {
        return new ResponseEntity<>(ordersService.addOrder(newOrders),HttpStatus.CREATED);
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Orders> one(@PathVariable Integer id) {
        return new ResponseEntity<>(ordersService.getOrderById(id),HttpStatus.OK);
    }

    @PutMapping("/orders/{id}")
    public ResponseEntity<Orders> replaceOrders(@RequestBody @Valid Orders newOrders, @PathVariable Integer id) {
        return new ResponseEntity<>(ordersService.updateOrder(newOrders,id),HttpStatus.OK);
    }

    @DeleteMapping("/orders/{id}")
    public void deleteOrders(@PathVariable Integer id) {
        ordersService.deleteOrder(id);
    }

    @GetMapping("/orders/user/{id}")
    public ResponseEntity<List<Orders>> getOrdersFromUser(@PathVariable Integer id){
        Orders orders = ordersService.getOrdersFromUser(id).get(0);
        System.out.println(ordersService.getOrdersFromUser(id));
        //return null;
        return ResponseEntity.ok(ordersService.getOrdersFromUser(id));
    }
}
