package ba.unsa.etf.nwt.order_service.repository;

import ba.unsa.etf.nwt.order_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
