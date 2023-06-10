package ba.unsa.etf.nwt.item_service.Controller;

import ba.unsa.etf.nwt.item_service.Model.Item;
import ba.unsa.etf.nwt.item_service.Model.ItemCategory;
import ba.unsa.etf.nwt.item_service.Model.Specifications;
import ba.unsa.etf.nwt.item_service.Repository.ItemCategoryRepository;
import ba.unsa.etf.nwt.item_service.Repository.ItemRepository;
import ba.unsa.etf.nwt.item_service.Repository.SpecificationsRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(path = "/api/categories")
public class ItemCategoryController {
    private final ItemCategoryRepository repository;
    ItemCategoryController(ItemCategoryRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/categories")
    List<ItemCategory> all() {
        return repository.findAll();
    }

    @PostMapping("/add/category")
    ItemCategory newCategory(@RequestBody ItemCategory category) {
        return repository.save(category);
    }



}
