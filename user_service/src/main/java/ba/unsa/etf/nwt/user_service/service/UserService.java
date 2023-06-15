package ba.unsa.etf.nwt.user_service.service;

import ba.unsa.etf.nwt.user_service.domain.User;
import ba.unsa.etf.nwt.user_service.model.UserDTO;
import ba.unsa.etf.nwt.user_service.repository.UserRepository;
import ba.unsa.etf.nwt.user_service.util.NotFoundException;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> findAll() {
        final List<User> users = userRepository.findAll(Sort.by("id"));
        return users.stream()
                .map((user) -> mapToDTO(user, new UserDTO()))
                .toList();
    }

    public UserDTO get(final Integer id) {
        return userRepository.findById(id)
                .map(user -> mapToDTO(user, new UserDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final UserDTO userDTO) {
        final User user = new User();
        mapToEntity(userDTO, user);
        return userRepository.save(user).getId();
    }

    public void update(final Integer id, final UserDTO userDTO) {
        final User user = userRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(userDTO, user);
        userRepository.save(user);
    }

    public Integer delete(final Integer id) {
        userRepository.deleteById(id);
        return id;
    }

    private UserDTO mapToDTO(final User user, final UserDTO userDTO) {
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setRole(user.getRole() == null ? null : user.getRole());
        return userDTO;
    }

    private User mapToEntity(final UserDTO userDTO, final User user) {
        user.setName(userDTO.getName());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setRole(userDTO.getRole());
        return user;
    }

    public boolean usernameExists(final String username) {
        return userRepository.existsByUsernameIgnoreCase(username);
    }

    public boolean emailExists(final String email) {
        return userRepository.existsByEmailIgnoreCase(email);
    }

    public UserDTO getUserByUsername(final String username) {
        Optional<User> userOptional = userRepository.getUserByUsername(username);
        User user = userOptional.map(User::new).orElseThrow(()-> new UsernameNotFoundException("User not found"));
        return mapToDTO(user ,new UserDTO());
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.getUserByUsername(username);
        User user = userOptional.map(User::new).orElseThrow(()-> new UsernameNotFoundException("User not found"));
        return user;
    }
}