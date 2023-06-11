package ba.unsa.etf.nwt.itemcart_service.service;

import ba.unsa.etf.nwt.itemcart_service.exception.NotFoundException;
import ba.unsa.etf.nwt.itemcart_service.model.Cart;
import ba.unsa.etf.nwt.itemcart_service.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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

    public List<Cart> getAllCart() {
        return cartRepository.findAll();
    }

    public Cart getCartById(Integer id){
        return cartRepository.findById(id).orElseThrow(()-> new NotFoundException(id,"cart"));
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

    public Integer create(final Cart cart) {
        final Cart newCart = new Cart();
        Integer cartId = null;
        ServiceInstance serviceInstanceUser = discoveryClient.getInstances("user-service").get(0);
        String resourceURL = serviceInstanceUser.getUri() + "/api/user/";
        boolean userExist = false;
        try{
            ResponseEntity<String> response= restTemplate.getForEntity(resourceURL+cart.getUserId(), String.class);
            if (response.getStatusCode().equals(HttpStatus.OK)) {
                userExist = true;

            }
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with given id doesn't exist");
        }
        if(userExist)
        {
            newCart.setUserId(cart.getUserId());
            cartId = cartRepository.save(newCart).getId();
        }
        return cartId;
    }

    public Cart updateCart(Cart newCart, Integer id){
        Cart oldCart = getCartById(id);
        oldCart.setItemCarts(newCart.getItemCarts());
        oldCart.setUserId(newCart.getUserId());
        cartRepository.save(oldCart);
        return oldCart;
    }

    public Cart getCartByUserId(Integer userId) {
        return cartRepository.getCartByUserId(userId);
    }
}
