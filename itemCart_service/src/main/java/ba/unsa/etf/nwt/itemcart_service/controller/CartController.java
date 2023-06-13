package ba.unsa.etf.nwt.itemcart_service.controller;

import ba.unsa.etf.nwt.itemcart_service.DTO.CartDTO;
import ba.unsa.etf.nwt.itemcart_service.model.Cart;
import ba.unsa.etf.nwt.itemcart_service.service.CartService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping(path = "/api/carts")
@RestController
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PreAuthorize("hasAuthority('admin:read')")
    @GetMapping()
    public ResponseEntity<List<CartDTO>> all() {
        return new ResponseEntity<>(cartService.getAllCart(), HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('customer:read')")
    @GetMapping("/cart/{id}")
    ResponseEntity<Cart> one(@PathVariable Integer id) {
        return new ResponseEntity<>(cartService.getCartById(id),HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('customer:read')")
    @GetMapping("/cart/user/{id}")
    ResponseEntity<Cart> getCartIdByUserId(@PathVariable Integer id) {
        return new ResponseEntity<>(cartService.getCartByUserId(id),HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('customer:create')")
    @PostMapping("/cart")
    public ResponseEntity<Integer> createCart(@RequestBody @Valid CartDTO cart) {
        return new ResponseEntity<>(cartService.create(cart),HttpStatus.CREATED);
    }
}
