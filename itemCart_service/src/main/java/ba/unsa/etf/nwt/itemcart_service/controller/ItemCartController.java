package ba.unsa.etf.nwt.itemcart_service.controller;

import ba.unsa.etf.nwt.itemcart_service.DTO.ItemCartDTO;
import ba.unsa.etf.nwt.itemcart_service.model.ItemCart;
import ba.unsa.etf.nwt.itemcart_service.response.GetItemsResponse;
import ba.unsa.etf.nwt.itemcart_service.service.ItemCartService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping(path = "/api/item_carts")
@Controller
public class ItemCartController {
    private final ItemCartService itemCartService;

    public ItemCartController(ItemCartService itemCartService) {
        this.itemCartService = itemCartService;
    }

    //@PreAuthorize("hasAuthority('admin:read')")
    @GetMapping()
    public ResponseEntity<List<ItemCartDTO>> all() {
        return new ResponseEntity<>(itemCartService.getAllItemCarts(), HttpStatus.OK);
    }
    //@PreAuthorize("hasAuthority('customer:create')")
    @PostMapping("/item_cart")
    public ResponseEntity<Integer> newItemCart(@RequestBody @Valid ItemCartDTO newCart) {
        return new ResponseEntity<>(itemCartService.add(newCart),HttpStatus.CREATED);
    }
    //@PreAuthorize("hasAuthority('customer:read')")
    @GetMapping("/item_cart/{id}")
    public ResponseEntity<ItemCart> one(@PathVariable Integer id) {
        return new ResponseEntity<>(itemCartService.getItemCartById(id),HttpStatus.OK);
    }
    //@PreAuthorize("hasAuthority('customer:delete')")
    @DeleteMapping("/item_cart/{id}")
    public void delete(@PathVariable Integer id) {
        itemCartService.deleteItemCart(id);
    }
    //@PreAuthorize("hasAuthority('customer:delete')")
    @DeleteMapping("/item_cart/{order_id}")
    public void deleteByOrderId(@PathVariable Integer order_id){
        itemCartService.deleteByOrderId(order_id);
    }
    //@PreAuthorize("hasAuthority('customer:read')")
    @GetMapping("/item_cart/order/{order_id}")
    public ResponseEntity<List<GetItemsResponse>> itemsByOrderId(@PathVariable Integer order_id) {
        return new ResponseEntity<>(itemCartService.getAllByOrderId(order_id),HttpStatus.OK);
    }
    //@PreAuthorize("hasAuthority('customer:read')")
    @GetMapping("/item_cart/{itemId}/cartExist")
    public ResponseEntity<Boolean> orderExist(@PathVariable Integer itemId){
        return new ResponseEntity<>(itemCartService.cartExistWithItem(itemId),HttpStatus.OK);
    }

}
