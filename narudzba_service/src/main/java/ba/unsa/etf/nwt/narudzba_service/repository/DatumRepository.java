package ba.unsa.etf.nwt.narudzba_service.repository;

import ba.unsa.etf.nwt.narudzba_service.model.Datum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatumRepository extends JpaRepository<Datum,Integer> {
}
