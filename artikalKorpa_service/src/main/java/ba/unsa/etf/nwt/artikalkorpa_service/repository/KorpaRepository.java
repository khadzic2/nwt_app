package ba.unsa.etf.nwt.artikalkorpa_service.repository;

import ba.unsa.etf.nwt.artikalkorpa_service.model.Korpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KorpaRepository extends JpaRepository<Korpa,Integer> {
}
