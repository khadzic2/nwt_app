package ba.unsa.etf.nwt.order_service.controller;
import java.util.List;
import ba.unsa.etf.nwt.order_service.exception.NotFoundException;
import ba.unsa.etf.nwt.order_service.model.User;
import ba.unsa.etf.nwt.order_service.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController

public class UserController {

    private final UserRepository repository;

    UserController(UserRepository repository) {
        this.repository = repository;
    }
    @GetMapping("/users")
    List<User> all() {
        return repository.findAll();
    }
    @PostMapping("/user")
    User newUser(@RequestBody User user) {
        return repository.save(user);
    }
    @GetMapping("/user/{id}")
    User one(@PathVariable Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(id,"user"));
    }
}
