package ba.unsa.etf.nwt.user_service.service;


import ba.unsa.etf.nwt.user_service.exception.NotFoundException;
import ba.unsa.etf.nwt.user_service.model.User;
import ba.unsa.etf.nwt.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Integer id){
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(id,"user"));
    }

    public User addUser(User user){
        return userRepository.save(user);
    }
}
