package ba.unsa.etf.nwt.order_service.controller;

import ba.unsa.etf.nwt.order_service.DTO.OrderItemsDTO;
import ba.unsa.etf.nwt.order_service.service.OrderItemsService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/orderitems", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderItemsController {
    private final OrderItemsService orderItemsService;

    public OrderItemsController(OrderItemsService orderItemsService) {
        this.orderItemsService = orderItemsService;
    }
    @PreAuthorize("hasAuthority('customer:read')")
    @GetMapping("/orderitems")
    public ResponseEntity<List<OrderItemsDTO>> all() {
        return new ResponseEntity<>(orderItemsService.getAllOrderItems(), HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('customer:create')")
    @PostMapping("/orderitems")
    public ResponseEntity<Integer> newOrderItems(@RequestBody @Valid OrderItemsDTO newOrderItems) {
        return new ResponseEntity<>(orderItemsService.addOrderItems(newOrderItems),HttpStatus.CREATED);
    }
    @PreAuthorize("hasAuthority('customer:read')")
    @GetMapping("/orderitems/{id}")
    public ResponseEntity<OrderItemsDTO> one(@PathVariable Integer id) {
        return new ResponseEntity<>(orderItemsService.getOrderItemsById(id),HttpStatus.OK);
    }
    @DeleteMapping("/orderitems/{id}")
    public ResponseEntity<String> deleteOrderItems(@PathVariable Integer id) {
        orderItemsService.deleteOrderItems(id);
        return new ResponseEntity<>("Successfully deleted!",HttpStatus.OK);
    }
}
