package ba.unsa.etf.nwt.item_service.Service;

import ba.unsa.etf.nwt.item_service.DTO.ItemDTO;
import ba.unsa.etf.nwt.item_service.Exceptions.NotFoundException;
import ba.unsa.etf.nwt.item_service.Model.Image;
import ba.unsa.etf.nwt.item_service.Model.Item;
import ba.unsa.etf.nwt.item_service.Model.ItemCategory;
import ba.unsa.etf.nwt.item_service.Model.Specifications;
import ba.unsa.etf.nwt.item_service.Repository.ImageRepository;
import ba.unsa.etf.nwt.item_service.Repository.ItemCategoryRepository;
import ba.unsa.etf.nwt.item_service.Repository.ItemRepository;
import ba.unsa.etf.nwt.item_service.Repository.SpecificationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {
    @Autowired
    private final ItemRepository itemRepository;
    @Autowired
    private final ImageRepository imageRepository;
    @Autowired
    private final ItemCategoryRepository itemCategoryRepository;
    @Autowired
    private final SpecificationsRepository specificationsRepository;
    @Autowired
    private RestTemplate restTemplate;

    private final DiscoveryClient discoveryClient;

    public ItemService(RestTemplate restTemplate, DiscoveryClient discoveryClient, ItemRepository itemRepository, ImageRepository imageRepository, ItemCategoryRepository itemCategoryRepository, SpecificationsRepository specificationsRepository) {
        this.itemRepository = itemRepository;
        this.imageRepository = imageRepository;
        this.itemCategoryRepository = itemCategoryRepository;
        this.specificationsRepository = specificationsRepository;
        this.restTemplate = restTemplate;
        this.discoveryClient = discoveryClient;
    }

    public List<ItemDTO> getAllItems(){
        return itemRepository.findAll().stream().map(item -> mapToDTO(item, new ItemDTO())).collect(Collectors.toList());
    }

    public Integer addItem(ItemDTO itemDTO){
        Item item = new Item();
        mapToEntity(itemDTO,item);
        return itemRepository.save(item).getId();
    }

    public ItemDTO getItemById(Integer id){
        return itemRepository.findById(id).map(item->mapToDTO(item,new ItemDTO())).orElseThrow(()-> new NotFoundException(id,"item"));
    }

    public void deleteItem(Integer id){itemRepository.deleteById(id);}

    public void deleteAll(){
        itemRepository.deleteAll();
    }

    public Integer getDaysByItem(Integer id) {
        return itemRepository.getDaysByItem(id);
    }

    public List<ItemDTO> getItemsFromCategory(Integer idCat){
        return itemRepository.getItemsFromCategory(idCat).stream().map(i->mapToDTO(i,new ItemDTO())).collect(Collectors.toList());
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
        ServiceInstance serviceInstance = discoveryClient.getInstances("orders-service").get(0);
        String resourceURL = serviceInstance.getUri() + "/api/order/" + id + "/orderExist";
        ResponseEntity<Boolean> response = restTemplate.getForEntity(resourceURL, Boolean.class);
        Boolean cartExistence = response.getBody();
        if (cartExistence != null && cartExistence == true) {
            exist = true;
        }
        return exist;
    }

    private void mapToEntity(final ItemDTO itemDTO, final Item item) {
        item.setName(itemDTO.getName());
        item.setDescription(itemDTO.getDescription());
        item.setManufacturingdays(itemDTO.getManufacturingdays());
        item.setPrice(itemDTO.getPrice());
        Image image = imageRepository.findById(itemDTO.getImageId()).orElseThrow(()->new NotFoundException(itemDTO.getImageId(),"image"));
        ItemCategory itemCategory = itemCategoryRepository.findById(itemDTO.getItemCategoryId()).orElseThrow(()->new NotFoundException(itemDTO.getItemCategoryId(),"category"));
        Specifications specifications = specificationsRepository.findById(itemDTO.getSpecificationsId()).orElseThrow(()->new NotFoundException(itemDTO.getSpecificationsId(),"specifications"));
        item.setImage(image);
        item.setCategory(itemCategory);
        item.setSpecifications(specifications);
    }

    private ItemDTO mapToDTO(final Item item, final ItemDTO itemDTO) {
        itemDTO.setId(item.getId());
        itemDTO.setName(item.getName());
        itemDTO.setDescription(item.getDescription());
        itemDTO.setManufacturingdays(item.getManufacturingdays());
        itemDTO.setPrice(item.getPrice());
        itemDTO.setItemCategoryId(item.getItemCategory().getId());
        itemDTO.setSpecificationsId(item.getSpecifications().getId());
        itemDTO.setImageId(item.getImage().getId());
        return itemDTO;
    }

}