package ba.unsa.etf.nwt.system_events_service.repositories;

import ba.unsa.etf.nwt.system_events_service.models.Action;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ActionRepository extends JpaRepository<Action, Integer> {
}
