package ba.unsa.etf.nwt.item_service.Service;

import ba.unsa.etf.nwt.item_service.Exceptions.NotFoundException;
import ba.unsa.etf.nwt.item_service.Model.Item;
import ba.unsa.etf.nwt.item_service.Repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getAllItems(){
        return itemRepository.findAll();
    }

    public Item getItemById(Integer id){
        return itemRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
    }

    public Item addItem(Item items){
        return itemRepository.save(items);
    }

    public void deleteItem(Integer id){
        itemRepository.deleteById(id);
    }

    public void deleteAll(){
        itemRepository.deleteAll();
    }

    public Item updateItem(Item newItem, Integer id){
        Item oldItem = getItemById(id);

        oldItem.setName(newItem.getName());
        oldItem.setDescription(newItem.getDescription());
        oldItem.setStatus(newItem.getStatus());
        oldItem.setManufacturingdays(newItem.getManufacturingdays());

        itemRepository.save(oldItem);
        return oldItem;
    }

    public List<Item> getItemsFromCart(Integer cartId){
        return itemRepository.getItemsFromCart(cartId);
    }
}