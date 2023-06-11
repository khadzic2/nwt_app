package ba.unsa.etf.nwt.itemcart_service.repository;

import ba.unsa.etf.nwt.itemcart_service.model.ItemCart;
import ba.unsa.etf.nwt.itemcart_service.response.GetItemsResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ItemCartRepository extends JpaRepository<ItemCart,Integer> {

    @Query(value = "SELECT * from item_cart it where it.order_id=:orderId", nativeQuery = true)
    List<ItemCart> getAllByOrderId(@Param("orderId") Integer orderId);

    @Query(value = "select count(it.item_id) as NoOfCarts from item_cart it where it.item_id=?1",nativeQuery = true)
    Integer countCartForItem(Integer id);

    @Modifying
    @Transactional
    @Query(value = "delete from item_cart i where i.order_id =?1",nativeQuery = true)
    void deleteByOrderId(Integer orderId);
}