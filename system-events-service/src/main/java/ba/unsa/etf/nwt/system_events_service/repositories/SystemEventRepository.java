package ba.unsa.etf.nwt.system_events_service.repositories;

import ba.unsa.etf.nwt.system_events_service.models.SystemEvent;
import org.springframework.data.repository.CrudRepository;

public interface SystemEventRepository extends CrudRepository<SystemEvent, Long> {
}
