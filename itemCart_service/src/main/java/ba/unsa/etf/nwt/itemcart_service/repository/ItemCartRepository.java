package ba.unsa.etf.nwt.itemcart_service.repository;

import ba.unsa.etf.nwt.itemcart_service.model.ItemCart;
import ba.unsa.etf.nwt.itemcart_service.response.GetItemsResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemCartRepository extends JpaRepository<ItemCart,Integer> {

    @Query(value = "DELETE FROM item_cart it where it.orderId =:orderId", nativeQuery = true)
    void deleteByOrderId(@Param("orderId") Integer orderId);

    @Query(value = "SELECT * from item_cart it where it.order_id=:orderId", nativeQuery = true)
    List<ItemCart> getAllByOrderId(@Param("orderId") Integer orderId);
}