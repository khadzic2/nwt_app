package ba.unsa.etf.nwt.item_service.Repository;

import ba.unsa.etf.nwt.item_service.Model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ItemRepository extends JpaRepository<Item,Integer> {

    @Query(value = "SELECT * FROM item i where i.item_category=?1 ", nativeQuery = true)
    List<Item> getItemsFromCategory(@Param("idCategory") Integer idCategory);

    @Query(value = "select i.manufacturingdays from item i where i.id=?1", nativeQuery = true)
    Integer getDaysByItem(Integer id);

}
