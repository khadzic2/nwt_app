package ba.unsa.etf.nwt.itemcart_service.controller;

import ba.unsa.etf.nwt.itemcart_service.model.Cart;
import ba.unsa.etf.nwt.itemcart_service.model.Item;
import ba.unsa.etf.nwt.itemcart_service.service.CartService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/carts")
    ResponseEntity<List<Cart>> all() {
        return new ResponseEntity<>(cartService.getAllCart(), HttpStatus.OK);
    }
    @PostMapping("/cart")
    ResponseEntity<Cart> newItem(@Valid @RequestBody Cart cart) {
        return new ResponseEntity<>(cartService.addCart(cart),HttpStatus.CREATED);
    }
    @GetMapping("/cart/{id}")
    ResponseEntity<Cart> one(@PathVariable Integer id) {
        return new ResponseEntity<>(cartService.getCartById(id),HttpStatus.OK);
    }
}
