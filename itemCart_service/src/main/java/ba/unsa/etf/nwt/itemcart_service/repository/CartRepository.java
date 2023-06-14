package ba.unsa.etf.nwt.itemcart_service.repository;

import ba.unsa.etf.nwt.itemcart_service.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CartRepository extends JpaRepository<Cart,Integer> {
    @Query(value = "select * from cart c where c.user_id=?1", nativeQuery = true)
    Cart getCartByUserId(Integer user_id);

    @Query(value = "select * from cart c where c.id=?1", nativeQuery = true)
    Cart findCartById(Integer id);
}
