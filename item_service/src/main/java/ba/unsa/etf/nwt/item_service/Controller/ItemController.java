package ba.unsa.etf.nwt.item_service.Controller;


import java.util.List;

import ba.unsa.etf.nwt.item_service.Exceptions.NotFoundException;
import ba.unsa.etf.nwt.item_service.Model.Item;
import ba.unsa.etf.nwt.item_service.Repository.ItemRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController

public class ItemController {

    private final ItemRepository repository;

    ItemController(ItemRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/items")
    List<Item> all() {
        return repository.findAll();
    }


    @PostMapping("/item")
    Item newItem(@RequestBody Item item) {
        return repository.save(item);
    }

    @GetMapping("/item/{id}")
    Item one(@PathVariable Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }


}
