package ba.unsa.etf.nwt.itemcart_service.service;

import ba.unsa.etf.nwt.itemcart_service.DTO.ItemCartDTO;
import ba.unsa.etf.nwt.itemcart_service.exception.NotFoundException;
import ba.unsa.etf.nwt.itemcart_service.model.ItemCart;
import ba.unsa.etf.nwt.itemcart_service.model.SelectedSpecifications;
import ba.unsa.etf.nwt.itemcart_service.repository.ItemCartRepository;
import ba.unsa.etf.nwt.itemcart_service.response.GetItemsResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemCartService {
    private final ItemCartRepository itemCartRepository;

    public ItemCartService(ItemCartRepository itemCartRepository) {
        this.itemCartRepository = itemCartRepository;
    }

    public List<ItemCartDTO> getAllItemCarts(){
        return itemCartRepository.findAll().stream().map(date -> mapToDTO(date, new ItemCartDTO())).collect(Collectors.toList());
    }

    public ItemCart getItemCartById(Integer id){
        return itemCartRepository.findById(id).orElseThrow(()-> new NotFoundException(id,"cart"));
    }

    public Integer add(ItemCartDTO cartDTO){
        ItemCart itemCart = new ItemCart();
        mapToEntity(cartDTO,itemCart);
        return itemCartRepository.save(itemCart).getId();
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

    private ItemCartDTO mapToDTO(final ItemCart itemCart, final ItemCartDTO itemCartDTO) {
        itemCartDTO.setId(itemCart.getId());
        itemCartDTO.setCartId(itemCart.getCart().getId());
        itemCartDTO.setItemId(itemCart.getItemId());
        itemCartDTO.setOrderId(itemCart.getOrderId());
        return itemCartDTO;
    }

    private void mapToEntity(final ItemCartDTO itemCartDTO, final ItemCart itemCart) {
        itemCart.setItemId(itemCartDTO.getItemId());
        itemCart.setOrderId(itemCartDTO.getOrderId());
    }
}
