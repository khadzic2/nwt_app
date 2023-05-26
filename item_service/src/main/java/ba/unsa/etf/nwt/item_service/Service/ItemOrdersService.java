package ba.unsa.etf.nwt.item_service.Service;

import ba.unsa.etf.nwt.item_service.Exceptions.NotFoundException;
import ba.unsa.etf.nwt.item_service.Model.ItemOrders;
import ba.unsa.etf.nwt.item_service.Repository.ItemOrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemOrdersService {
    @Autowired
    private final ItemOrdersRepository itemOrdersRepository;

    public ItemOrdersService(ItemOrdersRepository itemOrdersRepository) {
        this.itemOrdersRepository = itemOrdersRepository;
    }

    public List<ItemOrders> getAllOrders(){
        return itemOrdersRepository.findAll();
    }

    public ItemOrders getOrderById(Integer id){
        return itemOrdersRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
    }

    public ItemOrders addOrder(ItemOrders orders){
        return itemOrdersRepository.save(orders);
    }

    public void deleteOrder(Integer id){
        itemOrdersRepository.deleteById(id);
    }

    public void deleteAll(){
        itemOrdersRepository.deleteAll();
    }

}