package ba.unsa.etf.nwt.itemcart_service.service;

import ba.unsa.etf.nwt.itemcart_service.exception.NotFoundException;
import ba.unsa.etf.nwt.itemcart_service.model.Cart;
import ba.unsa.etf.nwt.itemcart_service.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public List<Cart> getAllCart(){
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

    public Cart updateCart(Cart newCart, Integer id){
        Cart oldCart = getCartById(id);
        oldCart.setItems(newCart.getItems());
        oldCart.setUser(newCart.getUser());
        cartRepository.save(oldCart);
        return oldCart;
    }


}
