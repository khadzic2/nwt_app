package ba.unsa.etf.nwt.item_service.Repository;

import ba.unsa.etf.nwt.item_service.Model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ItemRepository extends JpaRepository<Item,Integer> {

    @Query(value = "SELECT * FROM Item i where i.item_category_id=:itemCategory", nativeQuery = true)
    List<Item> getItemsFromCategory(@Param("itemCategory") String itemCategory);

}
