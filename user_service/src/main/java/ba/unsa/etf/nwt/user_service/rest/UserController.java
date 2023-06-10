package ba.unsa.etf.nwt.user_service.rest;

import ba.unsa.etf.nwt.user_service.model.UserDTO;
import ba.unsa.etf.nwt.user_service.service.UserService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.security.authorization.AuthorityReactiveAuthorizationManager.hasRole;


@RestController
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
@ComponentScan("ba.unsa.etf.nwt.user_service")
@PreAuthorize("hasRole('ADMIN')")
public class UserController {

    private final UserService userService;

    public UserController(final UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/sayHello")
    public String sayHello() {
        return ("Hello from userController");
    }

    @PostMapping("/register")
    public ResponseEntity<?> postUser(@RequestBody @Valid final UserDTO userDTO){
        try{
            return new ResponseEntity<>(userService.create(userDTO), HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<UserDTO> getUser(@PathVariable final UUID id) {
        return ResponseEntity.ok(userService.get(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<UUID> createUser(@RequestBody @Valid final UserDTO userDTO) {
        return new ResponseEntity<>(userService.create(userDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<Void> updateUser(@PathVariable final UUID id,
            @RequestBody @Valid final UserDTO userDTO) {
        userService.update(id, userDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('admin:delete')")
    public ResponseEntity<UUID> deleteUser(@PathVariable final UUID id) {
        return new ResponseEntity<>(userService.delete(id), HttpStatus.OK);
    }

}
