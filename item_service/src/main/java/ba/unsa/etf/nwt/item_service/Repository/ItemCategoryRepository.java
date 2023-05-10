package ba.unsa.etf.nwt.item_service.Repository;

import ba.unsa.etf.nwt.item_service.Model.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemCategoryRepository extends JpaRepository<ItemCategory,Integer> {
}
