package ba.unsa.etf.nwt.order_service.controller;

import ba.unsa.etf.nwt.order_service.exception.NotFoundException;
import ba.unsa.etf.nwt.order_service.model.Orders;
import ba.unsa.etf.nwt.order_service.repository.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class OrderController {
    private final OrderRepository orderRepository;

    OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/orders")
    List<Orders> all() {
        return orderRepository.findAll();
    }

    @PostMapping("/orders")
    Orders newOrders(@RequestBody Orders newOrders) {
        return orderRepository.save(newOrders);
    }

    @GetMapping("/orders/{id}")
    Orders one(@PathVariable Integer id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id,"order"));
    }

    @PutMapping("/orders/{id}")
    Orders replaceOrders(@RequestBody Orders newOrders, @PathVariable Integer id) {

        return orderRepository.findById(id)
                .map(orders -> {
                    orders.setDate(newOrders.getDate());
                    orders.setState(newOrders.getState());
                    orders.setItemId(newOrders.getItemId());
                    orders.setUserId(newOrders.getUserId());
                    return orderRepository.save(orders);
                }).orElseThrow(() -> new NotFoundException(id,"order"));
//                .orElseGet(() -> {
//                    newDate.setId(id);
//                    return repository.save(newEmployee);
//                });
    }

    @DeleteMapping("/orders/{id}")
    void deleteOrders(@PathVariable Integer id) {
        orderRepository.deleteById(id);
    }
}
