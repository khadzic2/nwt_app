package ba.unsa.etf.nwt.item_service.Service;

import ba.unsa.etf.nwt.item_service.Exceptions.NotFoundException;
import ba.unsa.etf.nwt.item_service.Model.ItemCategory;
import ba.unsa.etf.nwt.item_service.Repository.ItemCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemCategoryService {
    @Autowired
    private final ItemCategoryRepository itemCategoryRepository;

    public ItemCategoryService(ItemCategoryRepository itemCategoryRepository) {
        this.itemCategoryRepository = itemCategoryRepository;
    }

    public List<ItemCategory> getAllCategories(){
        return itemCategoryRepository.findAll();
    }

    public ItemCategory getCategoryById(Integer id){
        return itemCategoryRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
    }

    public ItemCategory addCategory(ItemCategory categories){
        return itemCategoryRepository.save(categories);
    }

    public void deleteCategory(Integer id){
        itemCategoryRepository.deleteById(id);
    }

    public void deleteAll(){
        itemCategoryRepository.deleteAll();
    }

}