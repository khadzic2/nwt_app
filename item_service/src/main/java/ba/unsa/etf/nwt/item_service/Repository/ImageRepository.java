package ba.unsa.etf.nwt.item_service.Repository;

import ba.unsa.etf.nwt.item_service.Model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image,Integer> {
    Optional<Image> findByName(String fileName);
}
