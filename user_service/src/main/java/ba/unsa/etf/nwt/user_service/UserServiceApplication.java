package ba.unsa.etf.nwt.user_service;

//import ba.unsa.etf.userservice.interceptor.SystemEventHandlerInterceptor;
import ba.unsa.etf.nwt.user_service.repos.UserRepository;
import ba.unsa.etf.nwt.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
        import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = {"ba.unsa.etf.nwt.user_service.domain", "ba.unsa.etf.nwt.user_service.model",
        "ba.unsa.etf.nwt.user_service.repos", "ba.unsa.etf.nwt.user_service.rest", "ba.unsa.etf.nwt.user_service.service"})
public class UserServiceApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService = new UserService(userRepository);

    @Override
    public void run(String... args) throws Exception {
//      User u1 = new User("Admin", "admin", "admin@mail.com", "password", "Admin aplikacije.");
//      u1.setRole(Role.ADMIN);
//       u1 = userRepository.save(u1);
//
//      UserDTO userDTO= new UserDTO();
//        userDTO.setId(u1.getId());
//        userDTO.setName(u1.getName());
//        userDTO.setUsername(u1.getUsername());
//        userDTO.setEmail(u1.getEmail());
//        userDTO.setPassword(u1.getPassword());
//        userDTO.setBio(u1.getBio());
//        userDTO.setRole(u1.getRole());
//       userRepository.save(u1);


    }
}