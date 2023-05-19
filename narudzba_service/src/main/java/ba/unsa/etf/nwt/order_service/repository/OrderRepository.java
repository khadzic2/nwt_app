package ba.unsa.etf.nwt.order_service.repository;

import ba.unsa.etf.nwt.order_service.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders,Integer> {
    @Query(value = "SELECT * FROM Orders o where o.userId=:userId", nativeQuery = true)
    List<Orders> getOrdersFromUser(@Param("userId") Integer userId);
}
