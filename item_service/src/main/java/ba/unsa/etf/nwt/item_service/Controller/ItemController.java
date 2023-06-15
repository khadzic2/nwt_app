package ba.unsa.etf.nwt.item_service.Controller;


import java.util.List;

import ba.unsa.etf.nwt.item_service.DTO.ItemDTO;
import ba.unsa.etf.nwt.item_service.Service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/item", produces = MediaType.APPLICATION_JSON_VALUE)
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/items")
    public ResponseEntity<List<ItemDTO>> all() {
        return new ResponseEntity<>(itemService.getAllItems(), HttpStatus.OK);
    }

    @PostMapping("/item")
    public ResponseEntity<Integer> newItem(@RequestBody @Valid ItemDTO newItem) {
        return new ResponseEntity<>(itemService.addItem(newItem),HttpStatus.CREATED);
    }

    @GetMapping("/item/{id}")
    ResponseEntity<ItemDTO> one(@PathVariable Integer id) {
       return new ResponseEntity<>(itemService.getItemById(id),HttpStatus.OK);
    }

    @GetMapping("/item/{id}/days")
    ResponseEntity<Integer> getDaysByItem(@PathVariable Integer id) {
        return new ResponseEntity<>(itemService.getDaysByItem(id), HttpStatus.OK);
    }

    @GetMapping("/item/cart/cartExist/{id}")
    public ResponseEntity<Boolean> itemInCart(@PathVariable Integer id){
        return ResponseEntity.ok(itemService.itemInCart(id));
    }

    @GetMapping("/item/order/orderExist/{id}")
    public ResponseEntity<Boolean> itemInOrder(@PathVariable Integer id){
       return ResponseEntity.ok(itemService.itemInOrder(id));
    }

    @DeleteMapping("/item/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Integer id) {
        itemService.deleteItem(id);
        return new ResponseEntity<>("Successfully deleted!",HttpStatus.OK);
    }

    @GetMapping("/items/category")
    public ResponseEntity<List<ItemDTO>> getItemsFromCategory(@PathVariable Integer id){
        return new ResponseEntity<>(itemService.getItemsFromCategory(id),HttpStatus.OK);
    }

}
