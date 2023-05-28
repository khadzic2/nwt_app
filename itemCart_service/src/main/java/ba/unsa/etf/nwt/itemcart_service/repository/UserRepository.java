package ba.unsa.etf.nwt.itemcart_service.repository;

import ba.unsa.etf.nwt.itemcart_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
