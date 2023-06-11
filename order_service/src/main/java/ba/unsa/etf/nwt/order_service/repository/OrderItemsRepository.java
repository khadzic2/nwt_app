package ba.unsa.etf.nwt.order_service.repository;

import ba.unsa.etf.nwt.order_service.model.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderItemsRepository extends JpaRepository<OrderItems,Integer> {
        @Query(value = "select item_id from order_items o where o.order_id=?1", nativeQuery = true)
        List<Integer> getItemsFromOrder(Integer id);

        @Query(value = "select count(o.item_id) as NoOfOrders from order_items o where o.item_id=?1",nativeQuery = true)
        Integer countOrdersForItem(Integer id);

        @Modifying
        @Transactional
        @Query(value = "delete from order_items o where o.order_id =?1",nativeQuery = true)
        void deleteOrder(Integer orderId);
}
