package ba.unsa.etf.nwt.itemcart_service.controller;

import ba.unsa.etf.nwt.itemcart_service.model.ItemCart;
import ba.unsa.etf.nwt.itemcart_service.response.GetItemsResponse;
import ba.unsa.etf.nwt.itemcart_service.service.ItemCartService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping(path = "/api")
@Controller
public class ItemCartController {
    private final ItemCartService itemCartService;

    public ItemCartController(ItemCartService itemCartService) {
        this.itemCartService = itemCartService;
    }

    @GetMapping("/item_carts")
    public ResponseEntity<List<ItemCart>> all() {
        return new ResponseEntity<>(itemCartService.getAllItemCarts(), HttpStatus.OK);
    }
    @PostMapping("/item_cart")
    public ResponseEntity<ItemCart> newItemCart(@Valid @RequestBody ItemCart cart) {
        return new ResponseEntity<>(itemCartService.add(cart),HttpStatus.CREATED);
    }

    @GetMapping("/item_cart/{id}")
    public ResponseEntity<ItemCart> one(@PathVariable Integer id) {
        return new ResponseEntity<>(itemCartService.getItemCartById(id),HttpStatus.OK);
    }

    @DeleteMapping("/item_cart/{id}")
    public void delete(@PathVariable Integer id) {
        itemCartService.deleteItemCart(id);
    }

    @DeleteMapping("/item_cart/{order_id}")
    public void deleteByOrderId(@PathVariable Integer order_id){
        itemCartService.deleteByOrderId(order_id);
    }

    @GetMapping("/item_cart/order/{order_id}")
    public ResponseEntity<List<GetItemsResponse>> itemsByOrderId(@PathVariable Integer order_id) {
        return new ResponseEntity<>(itemCartService.getAllByOrderId(order_id),HttpStatus.OK);
    }
    @GetMapping("/item_cart/{itemId}/cartExist")
    public ResponseEntity<Boolean> orderExist(@PathVariable Integer itemId){
        return new ResponseEntity<>(itemCartService.cartExistWithItem(itemId),HttpStatus.OK);
    }

}
