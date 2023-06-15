package ba.unsa.etf.nwt.item_service.Repository;

import ba.unsa.etf.nwt.item_service.Model.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemCategoryRepository extends JpaRepository<ItemCategory,Integer> {

    @Query(value = "SELECT * FROM item_category c where c.room=:category_name", nativeQuery = true)
    List<ItemCategory> getCategoryByName(@Param("category_name") String category_name);

    @Query(value = "SELECT DISTINCT c.room FROM item_category c",nativeQuery = true)
    List<String> getCategoriesRooms();
}
