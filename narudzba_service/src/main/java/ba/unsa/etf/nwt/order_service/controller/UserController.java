package ba.unsa.etf.nwt.order_service.controller;

import ba.unsa.etf.nwt.order_service.model.User;
import ba.unsa.etf.nwt.order_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class UserController {

    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/users")
    ResponseEntity<List<User>> all() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }
    @PostMapping("/user")
    ResponseEntity<User> newUser(@Valid @RequestBody User user) {
        return new ResponseEntity<>(userService.addUser(user),HttpStatus.CREATED);
    }
    @GetMapping("/user/{id}")
    ResponseEntity<User> one(@PathVariable Integer id) {
        return new ResponseEntity<>(userService.getUserById(id),HttpStatus.OK);
    }
}
