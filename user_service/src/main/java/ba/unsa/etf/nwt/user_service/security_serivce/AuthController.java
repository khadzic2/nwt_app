package ba.unsa.etf.nwt.user_service.security_serivce;

import ba.unsa.etf.nwt.user_service.config.UserNotFoundException;
import ba.unsa.etf.nwt.user_service.domain.Role;
import ba.unsa.etf.nwt.user_service.model.UserDTO;
import ba.unsa.etf.nwt.user_service.model.UsernamePassword;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@ComponentScan("ba.unsa.etf.nwt.user_service")
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
    @Autowired
    private AuthConfig authConfig;
    @Autowired
    private AuthService authService;


    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<?> addNewUser(@RequestBody @Valid final UserDTO userDTO){
        try{
            return new ResponseEntity<>(authService.saveUser(userDTO), HttpStatus.CREATED);
        } catch (Exception e){

            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UsernamePassword user) {
        try {
            if(user.getUsername() == null || user.getPassword() == null) {
                throw new UserNotFoundException("UserName or Password is Empty");
            }
            UserDTO userDTO = authService.getUserByUsername(user.getUsername());

            if(userDTO == null || !authConfig.passwordEncoder.matches(user.getPassword(),userDTO.getPassword() ) ){
               throw new UserNotFoundException ("UserName or Password is Invalid");
            }

            Role role = userDTO.getRole();
            String generatedToken = authService.generateToken(userDTO.getUsername(), role);
            return new ResponseEntity<>(generatedToken, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/token")
    public ResponseEntity<?> getToken(@RequestBody UsernamePassword usernamePassword){
        try {
            Authentication authenticate = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    usernamePassword.getUsername(), usernamePassword.getPassword()
                            ));
            if(authenticate.isAuthenticated()) {
                UserDTO userDTO = authService.getUserByUsername(usernamePassword.getUsername());
                Role role = userDTO.getRole();
                return new ResponseEntity<>(authService.generateToken(usernamePassword.getUsername(), role), HttpStatus.OK);
            }
            else{
                throw new RuntimeException("Invalid access");
            }
        }catch(AuthenticationException e){
                return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
            }
    }

    @GetMapping("/validate")
    public ResponseEntity<String> validateToken( @RequestParam("token") String token){
        try {
            authService.validateToken(token);
            return new ResponseEntity<>("Token is valid", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }


    @GetMapping("/sayHello")
    @PreAuthorize("hasRole('ADMIN')")
    public String sayHello() {
        return ("Hello");
    }

}
