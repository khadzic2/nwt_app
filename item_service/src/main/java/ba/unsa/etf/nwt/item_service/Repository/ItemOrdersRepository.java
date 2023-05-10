package ba.unsa.etf.nwt.item_service.Repository;

import ba.unsa.etf.nwt.item_service.Model.ItemOrders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemOrdersRepository extends JpaRepository<ItemOrders,Integer> {
}
