package ba.unsa.etf.nwt.itemcart_service.service;

import ba.unsa.etf.nwt.itemcart_service.DTO.CartDTO;
import ba.unsa.etf.nwt.itemcart_service.DTO.ItemCartDTO;
import ba.unsa.etf.nwt.itemcart_service.model.Cart;
import ba.unsa.etf.nwt.itemcart_service.model.ItemCart;
import ba.unsa.etf.nwt.itemcart_service.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {
    @Autowired
    private final CartRepository cartRepository;

    @Autowired
    private RestTemplate restTemplate;

    private final DiscoveryClient discoveryClient;


    public CartService(CartRepository cartRepository, RestTemplate restTemplate, DiscoveryClient discoveryClient) {
        this.cartRepository = cartRepository;
        this.restTemplate = restTemplate;
        this.discoveryClient = discoveryClient;
    }

    public List<CartDTO> getAllCart(){
        return cartRepository.findAll().stream().map(date -> mapToDTO(date, new CartDTO())).collect(Collectors.toList());
    }

    public  Collection<ItemCartDTO> getCartItemsById(Integer id){
        Collection<ItemCartDTO> itemsDTO = new ArrayList<>();
        Cart cart = cartRepository.findCartById(id);
        for(ItemCart itemCart: cart.getItemCarts()){
            itemsDTO.add(new ItemCartDTO(itemCart.getCart().getId(), itemCart.getItemId(), itemCart.getSelectedSpecifications().getId(), itemCart.getOrderId()));
        }
        return itemsDTO;
    }

    public Cart addCart(Cart cart){
        return cartRepository.save(cart);
    }

    public void deleteCart(Integer id){
        cartRepository.deleteById(id);
    }

    public void deleteAll(){
        cartRepository.deleteAll();
    }

    public Integer create(final CartDTO cartDTO) {
        final Cart newCart = new Cart();
        Integer cartId = null;
        ServiceInstance serviceInstanceUser = discoveryClient.getInstances("user-service").get(0);
        String resourceURL = serviceInstanceUser.getUri() + "/api/users/user/";
        System.out.println(resourceURL);
        mapToEntity(cartDTO,newCart);
        boolean userExist = false;
        try{
            ResponseEntity<String> response= restTemplate.getForEntity(resourceURL+cartDTO.getUserId(), String.class);
            if (response.getStatusCode().equals(HttpStatus.OK)) {
                userExist = true;

            }
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with given id doesn't exist");
        }
        if(userExist)
        {
            newCart.setUserId(cartDTO.getUserId());
            cartId = cartRepository.save(newCart).getId();
        }
        return cartId;
    }

    public Cart getCartByUserId(Integer userId) {
        return cartRepository.getCartByUserId(userId);
    }

    private void mapToEntity(final CartDTO cartDTO, final Cart cart) {
        cart.setUserId(cartDTO.getUserId());
    }

    private CartDTO mapToDTO(final Cart cart, final CartDTO cartDTO) {
        cartDTO.setId(cart.getId());
        cartDTO.setUserId(cart.getUserId());
        return cartDTO;
    }
}
