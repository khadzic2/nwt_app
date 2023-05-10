package ba.unsa.etf.nwt.item_service.Repository;

import ba.unsa.etf.nwt.item_service.Model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Integer> {
}
