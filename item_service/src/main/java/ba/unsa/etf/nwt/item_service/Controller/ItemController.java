package ba.unsa.etf.nwt.item_service.Controller;


import java.util.List;

import ba.unsa.etf.nwt.item_service.DTO.ItemDTO;
import ba.unsa.etf.nwt.item_service.Exceptions.NotAllowedRequest;
import ba.unsa.etf.nwt.item_service.Exceptions.NotFoundException;
import ba.unsa.etf.nwt.item_service.Model.Item;
import ba.unsa.etf.nwt.item_service.Repository.ItemRepository;
import ba.unsa.etf.nwt.item_service.Service.ItemService;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import ba.unsa.etf.nwt.item_service.Exceptions.RestExceptionHandler;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/item", produces = MediaType.APPLICATION_JSON_VALUE)
public class ItemController {

    private final ItemRepository repository;
    private ItemService itemService;
    RestTemplate restTemplate;

    ItemController(ItemRepository repository) {
        this.repository = repository;
    }

    //@GetMapping("/items")
    //public ResponseEntity<List<ItemDTO>> all() {
      //  return new ResponseEntity<>(itemService.getAllItems(), HttpStatus.OK);
    //}
    @PreAuthorize("hasAnyAuthority('admin:read')")
    @GetMapping("/items")
    List<Item> all() {
        return repository.findAll();
    }
    @PreAuthorize("hasAnyAuthority('admin:create')")
    @PostMapping("/item")
    public ResponseEntity<Integer> newItem(@RequestBody @Valid ItemDTO newItem) {
        return new ResponseEntity<>(itemService.addItem(newItem),HttpStatus.CREATED);
    }

    @GetMapping("/item/{id}")
    Item one(@PathVariable Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    @GetMapping("/item/{id}/days")
    ResponseEntity<Integer> getDaysByItem(@PathVariable Integer id) {
        return new ResponseEntity<>(itemService.getDaysByItem(id), HttpStatus.OK);
    }
    @PreAuthorize("hasAnyAuthority('user:read')")
    @GetMapping("/item/cart/cartExist")
    public ResponseEntity<Boolean> itemInCart(@PathVariable Integer id){
        return ResponseEntity.ok(itemService.itemInCart(id));
    }
    @PreAuthorize("hasAnyAuthority('admin:create')")
    @GetMapping("/item/order/orderExist")
    public ResponseEntity<Boolean> itemInOrder(@PathVariable Integer id){
       return ResponseEntity.ok(itemService.itemInOrder(id));
    }

}
