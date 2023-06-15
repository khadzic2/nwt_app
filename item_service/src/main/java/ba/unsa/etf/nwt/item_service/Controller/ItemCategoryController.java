package ba.unsa.etf.nwt.item_service.Controller;

import ba.unsa.etf.nwt.item_service.DTO.ItemCategoryDTO;
import ba.unsa.etf.nwt.item_service.Service.ItemCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/categories")
public class ItemCategoryController {
    private final ItemCategoryService itemCategoryService;
    public ItemCategoryController(ItemCategoryService itemCategoryService) {
        this.itemCategoryService = itemCategoryService;
    }

    @GetMapping("/categories")
    public ResponseEntity<List<ItemCategoryDTO>> all() {
        return new ResponseEntity<>(itemCategoryService.getAllCategories(), HttpStatus.OK);
    }

    @PostMapping("/add/category")
    public ResponseEntity<Integer> newCategory(@RequestBody @Valid ItemCategoryDTO newCategory) {
        return new ResponseEntity<>(itemCategoryService.addCategory(newCategory),HttpStatus.CREATED);
    }

    @GetMapping("/rooms")
    public ResponseEntity<List<String>> getRooms() {
        return new ResponseEntity<>(itemCategoryService.getRooms(), HttpStatus.OK);
    }

    @GetMapping("/room/categories/")
    public ResponseEntity<List<ItemCategoryDTO>> getCategoriesByRoom(@RequestParam String room) {
        return new ResponseEntity<>(itemCategoryService.getCategoryByName(room), HttpStatus.OK);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<ItemCategoryDTO> getCategoryById(@PathVariable Integer id) {
        return new ResponseEntity<>(itemCategoryService.getCategoryById(id), HttpStatus.OK);
    }
}
