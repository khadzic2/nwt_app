package ba.unsa.etf.nwt.artikalkorpa_service.repository;

import ba.unsa.etf.nwt.artikalkorpa_service.model.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KorisnikRepository extends JpaRepository<Korisnik,Integer> {
}
