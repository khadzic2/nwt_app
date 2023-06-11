package ba.unsa.etf.nwt.item_service.Service;

import ba.unsa.etf.nwt.item_service.DTO.ItemCategoryDTO;
import ba.unsa.etf.nwt.item_service.DTO.ItemDTO;
import ba.unsa.etf.nwt.item_service.Exceptions.NotFoundException;
import ba.unsa.etf.nwt.item_service.Model.Item;
import ba.unsa.etf.nwt.item_service.Model.ItemCategory;
import ba.unsa.etf.nwt.item_service.Repository.ItemRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import ba.unsa.etf.nwt.item_service.Response.CartExistsResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {
    @Autowired
    private final ItemRepository itemRepository;
    @Autowired
    private RestTemplate restTemplate;

    private final DiscoveryClient discoveryClient;
    private List<Item> cart;


    public ItemService(RestTemplate restTemplate, DiscoveryClient discoveryClient, ItemRepository itemRepository) {
        this.itemRepository = itemRepository;

        this.restTemplate = restTemplate;
        this.discoveryClient = discoveryClient;
    }

    public List<ItemDTO> getAllItems(){
        return itemRepository.findAll().stream().map(orders -> mapToDTO(orders, new ItemDTO())).collect(Collectors.toList());
    }

    public Integer addItem(ItemDTO itemDTO){
        Item item = new Item();
        mapToEntity(itemDTO,item);
        return itemRepository.save(item).getId();
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
        oldItem.setManufacturingdays(newItem.getManufacturingdays());

        itemRepository.save(oldItem);
        return oldItem;
    }

    public Integer getDaysByItem(Integer id) {
        return itemRepository.getDaysByItem(id);
    }

    public Boolean itemInCart(Integer id){
        Boolean exists = false;
        ServiceInstance serviceInstance = discoveryClient.getInstances("item_cart-service").get(0);
        String resourceURL = serviceInstance.getUri() + "/api/item_carts/item_cart/" + id + "/cartExist";
        ResponseEntity<Integer> response = restTemplate.getForEntity(resourceURL, Integer.class);
        Integer cartExistence = response.getBody();
        if (cartExistence != null && cartExistence == 1) {
            exists = true;
        }
        return exists;
    }


    public Boolean itemInOrder(Integer id){
        Boolean exist = false;
        ServiceInstance serviceInstance = discoveryClient.getInstances("order-service").get(0);
        String resourceURL = serviceInstance.getUri() + "/api/order/" + id + "/orderExist";
        ResponseEntity<Integer> response = restTemplate.getForEntity(resourceURL, Integer.class);
        Integer cartExistence = response.getBody();
        if (cartExistence != null && cartExistence == 1) {
            exist = true;
        }
        return exist;
    }

    private void mapToEntity(final ItemDTO itemDTO, final Item item) {
        item.setName(itemDTO.getName());
        item.setDescription(itemDTO.getDescription());
        item.setManufacturingdays(itemDTO.getManufacturingdays());
        item.setPrice(itemDTO.getPrice());
    }

    private ItemDTO mapToDTO(final Item item, final ItemDTO itemDTO) {
        itemDTO.setId(item.getId());
        itemDTO.setName(item.getName());
        itemDTO.setDescription(item.getDescription());
        itemDTO.setManufacturingdays(item.getManufacturingdays());
        itemDTO.setPrice(item.getPrice());
        return itemDTO;
    }

}