package ba.unsa.etf.nwt.itemcart_service.controller;

import ba.unsa.etf.nwt.itemcart_service.model.Cart;
import ba.unsa.etf.nwt.itemcart_service.service.CartService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping(path = "/api")
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

    @GetMapping("/cart/{id}")
    ResponseEntity<Cart> one(@PathVariable Integer id) {
        return new ResponseEntity<>(cartService.getCartById(id),HttpStatus.OK);
    }

    @GetMapping("/cart/user/{id}")
    ResponseEntity<Cart> getCartIdByUserId(@PathVariable Integer id) {
        return new ResponseEntity<>(cartService.getCartByUserId(id),HttpStatus.OK);
    }

    @PostMapping("/cart")
    public ResponseEntity<Integer> createCart(@RequestBody @Valid Cart cart) {
        return new ResponseEntity<>(cartService.create(cart), HttpStatus.CREATED);
    }
}
