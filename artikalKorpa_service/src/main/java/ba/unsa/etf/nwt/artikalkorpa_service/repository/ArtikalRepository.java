package ba.unsa.etf.nwt.artikalkorpa_service.repository;

import ba.unsa.etf.nwt.artikalkorpa_service.model.Artikal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtikalRepository extends JpaRepository<Artikal,Integer> {
}
