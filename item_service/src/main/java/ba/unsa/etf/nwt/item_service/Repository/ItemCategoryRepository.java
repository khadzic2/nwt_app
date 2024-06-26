package ba.unsa.etf.nwt.item_service.Repository;

import ba.unsa.etf.nwt.item_service.Model.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;


public interface ItemCategoryRepository extends JpaRepository<ItemCategory,Integer> {

    @Query(value = "SELECT * FROM ItemCategory c where c.name=:category_name", nativeQuery = true)
    Optional<ItemCategory> getCategoryByName(@Param("category_name") String category_name);

}
