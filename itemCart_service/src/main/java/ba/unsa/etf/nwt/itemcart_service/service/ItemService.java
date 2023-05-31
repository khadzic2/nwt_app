package ba.unsa.etf.nwt.itemcart_service.service;


import ba.unsa.etf.nwt.itemcart_service.exception.NotFoundException;
import ba.unsa.etf.nwt.itemcart_service.model.Item;
import ba.unsa.etf.nwt.itemcart_service.repository.ItemRepository;
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
        return itemRepository.findById(id).orElseThrow(()-> new NotFoundException(id, "item"));
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

        itemRepository.save(oldItem);
        return oldItem;
    }
}