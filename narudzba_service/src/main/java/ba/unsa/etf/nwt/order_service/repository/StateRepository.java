package ba.unsa.etf.nwt.order_service.repository;

import ba.unsa.etf.nwt.order_service.model.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<State,Integer> {
}
