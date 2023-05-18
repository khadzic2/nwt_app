package ba.unsa.etf.nwt.user_service.service;

import ba.unsa.etf.nwt.user_service.exception.NotFoundException;
import ba.unsa.etf.nwt.user_service.model.Role;
import ba.unsa.etf.nwt.user_service.model.User;
import ba.unsa.etf.nwt.user_service.repository.RoleRepository;
import ba.unsa.etf.nwt.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleService {
    @Autowired
    private final RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getAllRoles(){
        return roleRepository.findAll();
    }

    public Role getRoleById(Integer id){
        return roleRepository.findById(id).orElseThrow(() -> new NotFoundException(id,"role"));
    }

    public Role addRole(Role role){
        return roleRepository.save(role);
    }

    public void addRoleToUser(String username, String roleName) {
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
        userRepository.save(user);
    }
}
