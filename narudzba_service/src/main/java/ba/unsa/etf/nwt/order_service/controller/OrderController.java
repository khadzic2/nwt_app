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
    ResponseEntity<List<Orders>> all() {
        return new ResponseEntity<>(ordersService.getAllOrders(), HttpStatus.OK);
    }

    @PostMapping("/orders")
    ResponseEntity<Orders> newOrders(@RequestBody @Valid Orders newOrders) {
        return new ResponseEntity<>(ordersService.addOrder(newOrders),HttpStatus.CREATED);
    }

    @GetMapping("/orders/{id}")
    ResponseEntity<Orders> one(@PathVariable Integer id) {
        return new ResponseEntity<>(ordersService.getOrderById(id),HttpStatus.OK);
    }

    @PutMapping("/orders/{id}")
    ResponseEntity<Orders> replaceOrders(@RequestBody @Valid Orders newOrders, @PathVariable Integer id) {
        return new ResponseEntity<>(ordersService.updateOrder(newOrders,id),HttpStatus.OK);
    }

    @DeleteMapping("/orders/{id}")
    void deleteOrders(@PathVariable Integer id) {
        ordersService.deleteOrder(id);
    }
}
