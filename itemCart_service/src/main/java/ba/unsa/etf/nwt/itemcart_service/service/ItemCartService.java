package ba.unsa.etf.nwt.itemcart_service.service;

import ba.unsa.etf.nwt.itemcart_service.exception.NotFoundException;
import ba.unsa.etf.nwt.itemcart_service.model.ItemCart;
import ba.unsa.etf.nwt.itemcart_service.model.SelectedSpecifications;
import ba.unsa.etf.nwt.itemcart_service.repository.ItemCartRepository;
import ba.unsa.etf.nwt.itemcart_service.response.GetItemsResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCartService {
    private final ItemCartRepository itemCartRepository;

    public ItemCartService(ItemCartRepository itemCartRepository) {
        this.itemCartRepository = itemCartRepository;
    }

    public List<ItemCart> getAllItemCarts(){
        return itemCartRepository.findAll();
    }

    public ItemCart getItemCartById(Integer id){
        return itemCartRepository.findById(id).orElseThrow(()-> new NotFoundException(id,"cart"));
    }

    public ItemCart add(ItemCart itemCart){
        return itemCartRepository.save(itemCart);
    }

    public void deleteItemCart(Integer id){
        itemCartRepository.deleteById(id);
    }

    public void deleteAll(){
        itemCartRepository.deleteAll();
    }

    public void deleteByOrderId(Integer orderId) {
        itemCartRepository.deleteByOrderId(orderId);
    }

    public List<GetItemsResponse> getAllByOrderId(Integer orderId) {
        List<ItemCart> itemCarts = itemCartRepository.getAllByOrderId(orderId);
        ArrayList<GetItemsResponse>itemsResponses = new ArrayList<GetItemsResponse>();
        for (ItemCart row : itemCarts) {
            SelectedSpecifications selectedSpecifications = row.getSelectedSpecifications();
            GetItemsResponse getItemsResponse = new GetItemsResponse(row.getItemId(), selectedSpecifications.getColor(),
                    selectedSpecifications.getHeight(), selectedSpecifications.getWidth(), selectedSpecifications.getDepth(),
                    selectedSpecifications.getMaterial());
            itemsResponses.add(getItemsResponse);
        }
        return itemsResponses;
    }
    public boolean cartExistWithItem(Integer itemId){
        return itemCartRepository.countCartForItem(itemId) != 0;
    }
}
