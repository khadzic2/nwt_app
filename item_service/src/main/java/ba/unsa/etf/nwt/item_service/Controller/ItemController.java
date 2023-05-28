package ba.unsa.etf.nwt.item_service.Controller;


import java.util.List;

import ba.unsa.etf.nwt.item_service.Exceptions.NotAllowedRequest;
import ba.unsa.etf.nwt.item_service.Exceptions.NotFoundException;
import ba.unsa.etf.nwt.item_service.Model.Item;
import ba.unsa.etf.nwt.item_service.Repository.ItemRepository;
import ba.unsa.etf.nwt.item_service.Service.ItemService;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import ba.unsa.etf.nwt.item_service.Exceptions.RestExceptionHandler;

@RestController
@RequestMapping(value = "/items", produces = MediaType.APPLICATION_JSON_VALUE)
public class ItemController {

    private final ItemRepository repository;
    private ItemService itemService;
    RestTemplate restTemplate;

    ItemController(ItemRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/items")
    List<Item> all() {
        return repository.findAll();
    }


    @PostMapping("/item")
    Item newItem(@RequestBody Item item) {
        return repository.save(item);
    }

    @GetMapping("/item/{id}")
    Item one(@PathVariable Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    @GetMapping(path = "/items_in_cart")
    public @ResponseBody Iterable<Item> getAllItems(){
        return itemService.getAllItems();
    }

    @PostMapping("/add")
    public Item AddToCart(int cartId, Item i){
        if (i.getStatus().equals(Item.StatusType.NOT_AVAILABLE)) throw new
                NotAllowedRequest("Item is not available");

        Item item = restTemplate.postForObject("user/cart/"+cartId, i, i.getClass());
        return item;
        ///api/cart
    }

    public void checkStock(String itemId) {
        String url = "http://stock-service-api.com/stock/{itemId}";

        ResponseEntity<Integer> response =
                restTemplate.exchange(url, HttpMethod.GET, null, Integer.class, itemId);

        if (response.getStatusCode().is2xxSuccessful()) {
            int stockQuantity = response.getBody();
            System.out.println("Item stock quantity: " + stockQuantity);
        } else {
            System.out.println("Failed to retrieve item stock quantity.");
        }
    }


    @GetMapping("/items/cart/{cart_id}")
    public ResponseEntity<List<Item>> getItemsFromCart(@PathVariable Integer id){
        Item item = itemService.getItemsFromCart(id).get(0);
        System.out.println(itemService.getItemsFromCart(id));
        //return null;
        return ResponseEntity.ok(itemService.getItemsFromCart(id));
    }


}
