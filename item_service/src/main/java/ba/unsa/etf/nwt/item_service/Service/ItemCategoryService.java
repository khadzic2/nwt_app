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

    public List<String> getRooms(){
        return itemCategoryRepository.getCategoriesRooms();
    }

    public List<ItemCategoryDTO> getCategoryByName(String room){
        return itemCategoryRepository.getCategoryByName(room).stream().map(c->mapToDTO(c,new ItemCategoryDTO())).collect(Collectors.toList());
    }

    public ItemCategoryDTO getCategoryById(Integer id){
        return itemCategoryRepository.findById(id).map(category->mapToDTO(category,new ItemCategoryDTO())).orElseThrow(()-> new NotFoundException(id,"category"));
    }

    public Integer addCategory(ItemCategoryDTO itemCategoryDTO){
        ItemCategory itemCategory = new ItemCategory();
        mapToEntity(itemCategoryDTO,itemCategory);
        return itemCategoryRepository.save(itemCategory).getId();
    }

    private void mapToEntity(final ItemCategoryDTO itemCategoryDTO, final ItemCategory itemCategory) {
        itemCategory.setRoom(itemCategoryDTO.getRoom());
        itemCategory.setTypeOfItem(itemCategoryDTO.getTypeOfItem());
    }

    private ItemCategoryDTO mapToDTO(final ItemCategory itemCategory, final ItemCategoryDTO itemCategoryDTO) {
        itemCategoryDTO.setId(itemCategory.getId());
        itemCategoryDTO.setRoom(itemCategory.getRoom());
        itemCategoryDTO.setTypeOfItem(itemCategory.getTypeOfItem());
        return itemCategoryDTO;
    }

}