package ba.unsa.etf.nwt.user_service.service;

import ba.unsa.etf.nwt.user_service.domain.User;
import ba.unsa.etf.nwt.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Optional<User> user = userRepository.getUserByUsername(username);
        return user.map(User::new).orElseThrow(()-> new UsernameNotFoundException("User not found"));
    }

}
