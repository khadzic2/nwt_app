package ba.unsa.etf.nwt.user_service.repository;

import ba.unsa.etf.nwt.user_service.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findByName(String roleName);
}
