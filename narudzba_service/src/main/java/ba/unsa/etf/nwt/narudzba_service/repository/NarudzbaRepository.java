package ba.unsa.etf.nwt.narudzba_service.repository;

import ba.unsa.etf.nwt.narudzba_service.model.Narudzba;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NarudzbaRepository extends JpaRepository<Narudzba,Integer> {
}
