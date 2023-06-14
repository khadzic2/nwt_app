package ba.unsa.etf.nwt.item_service.Service;

import ba.unsa.etf.nwt.item_service.DTO.ItemCategoryDTO;
import ba.unsa.etf.nwt.item_service.Exceptions.NotFoundException;
import ba.unsa.etf.nwt.item_service.Model.ItemCategory;
import ba.unsa.etf.nwt.item_service.Repository.ItemCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemCategoryService {
    @Autowired
    private final ItemCategoryRepository itemCategoryRepository;

    public ItemCategoryService(ItemCategoryRepository itemCategoryRepository) {
        this.itemCategoryRepository = itemCategoryRepository;
    }

    public List<ItemCategoryDTO> getAllCategories(){
        return itemCategoryRepository.findAll().stream().map(date -> mapToDTO(date, new ItemCategoryDTO())).collect(Collectors.toList());
    }

    public ItemCategory getCategoryById(Integer id){
        return itemCategoryRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
    }

    public ItemCategory addCategory(ItemCategory categories){
        return itemCategoryRepository.save(categories);
    }

    public Integer addCategory(ItemCategoryDTO itemCategoryDTO){
        ItemCategory itemCategory = new ItemCategory();
        mapToEntity(itemCategoryDTO,itemCategory);
        return itemCategoryRepository.save(itemCategory).getId();
    }

    public void deleteCategory(Integer id){
        itemCategoryRepository.deleteById(id);
    }

    public void deleteAll(){
        itemCategoryRepository.deleteAll();
    }

    private void mapToEntity(final ItemCategoryDTO itemCategoryDTO, final ItemCategory itemCategory) {
        itemCategory.setCategory_name(itemCategoryDTO.getCategory_name());
    }

    private ItemCategoryDTO mapToDTO(final ItemCategory itemCategory, final ItemCategoryDTO itemCategoryDTO) {
        itemCategoryDTO.setId(itemCategory.getId());
        itemCategoryDTO.setCategory_name(itemCategory.getCategory_name());
        return itemCategoryDTO;
    }

    public List<ItemCategory> getCategoriesForRoom(Integer roomId) {
        return itemCategoryRepository.findByItems_RoomId(roomId);
    }

}