package ba.unsa.etf.nwt.user_service.repos;

import java.util.Optional;
import java.util.UUID;

import ba.unsa.etf.nwt.user_service.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    boolean existsByUsernameIgnoreCase(String username);

    boolean existsByEmailIgnoreCase(String email);

    Optional<User> getUserByUsername(String username);

}
