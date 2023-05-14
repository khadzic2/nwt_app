package ba.unsa.etf.nwt.itemcart_service.controller;

import ba.unsa.etf.nwt.itemcart_service.exception.NotFoundException;
import ba.unsa.etf.nwt.itemcart_service.model.Item;
import ba.unsa.etf.nwt.itemcart_service.model.User;
import ba.unsa.etf.nwt.itemcart_service.repository.ItemRepository;
import ba.unsa.etf.nwt.itemcart_service.service.ItemService;
import ba.unsa.etf.nwt.itemcart_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController

public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/items")
    ResponseEntity<List<Item>> all() {
        return new ResponseEntity<>(itemService.getAllItems(), HttpStatus.OK);
    }
    @PostMapping("/item")
    ResponseEntity<Item> newItem(@Valid @RequestBody Item item) {
        return new ResponseEntity<>(itemService.addItem(item),HttpStatus.CREATED);
    }
    @GetMapping("/item/{id}")
    ResponseEntity<Item> one(@PathVariable Integer id) {
        return new ResponseEntity<>(itemService.getItemById(id),HttpStatus.OK);
    }

}
