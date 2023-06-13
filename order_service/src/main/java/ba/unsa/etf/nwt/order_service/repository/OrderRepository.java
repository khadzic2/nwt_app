package ba.unsa.etf.nwt.order_service.repository;

import ba.unsa.etf.nwt.order_service.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders,Integer> {
    @Query(value = "select * from orders o where o.user_id=?1", nativeQuery = true)
    List<Orders> getOrdersFromUser(Integer userId);
    @Query(value = "select date_id from orders o where o.id=?1", nativeQuery = true)
    Integer getDateIdForOrder(Integer id);
}
