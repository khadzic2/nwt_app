package ba.unsa.etf.nwt.item_service.Controller;

import ba.unsa.etf.nwt.item_service.DTO.ItemCategoryDTO;
import ba.unsa.etf.nwt.item_service.Model.Item;
import ba.unsa.etf.nwt.item_service.Model.ItemCategory;
import ba.unsa.etf.nwt.item_service.Model.Specifications;
import ba.unsa.etf.nwt.item_service.Repository.ItemCategoryRepository;
import ba.unsa.etf.nwt.item_service.Repository.ItemRepository;
import ba.unsa.etf.nwt.item_service.Repository.SpecificationsRepository;
import ba.unsa.etf.nwt.item_service.Service.ItemCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(path = "/api/categories")
public class ItemCategoryController {
    //private final ItemCategoryRepository repository;
    private final ItemCategoryService itemCategoryService;
    public ItemCategoryController(ItemCategoryService itemCategoryService) {
        this.itemCategoryService = itemCategoryService;
    }

    @GetMapping("/categories")
    public ResponseEntity<List<ItemCategoryDTO>> all() {
        return new ResponseEntity<>(itemCategoryService.getAllCategories(), HttpStatus.OK);
    }
    @PreAuthorize("hasAnyAuthority('admin:create')")
    @PostMapping("/add/category")
    public ResponseEntity<Integer> newCategory(@RequestBody @Valid ItemCategoryDTO newCategory) {
        return new ResponseEntity<>(itemCategoryService.addCategory(newCategory),HttpStatus.CREATED);
    }

    @GetMapping("/room/{roomId}")
    public List<ItemCategory> getCategoriesForRoom(@PathVariable Integer roomId) {
        return itemCategoryService.getCategoriesForRoom(roomId);
    }

    }
