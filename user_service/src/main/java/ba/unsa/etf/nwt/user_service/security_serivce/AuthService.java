package ba.unsa.etf.nwt.user_service.security_serivce;

import ba.unsa.etf.nwt.user_service.domain.Role;
import ba.unsa.etf.nwt.user_service.domain.User;
import ba.unsa.etf.nwt.user_service.model.UserDTO;
import ba.unsa.etf.nwt.user_service.repository.UserRepository;
import ba.unsa.etf.nwt.user_service.security_serivce.token.Token;
import ba.unsa.etf.nwt.user_service.security_serivce.token.TokenRepository;
import ba.unsa.etf.nwt.user_service.security_serivce.token.TokenType;
import ba.unsa.etf.nwt.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.stereotype.Service;


@Service
@ComponentScan("ba.unsa.etf.nwt.user_service")
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    //method to generate and validate a token
    @Autowired
    private JwtService jwtService;

    @Autowired
    private TokenRepository tokenRepository;


    @Autowired
    private AuthConfig authConfig;
    //inject-amo PasswordEncoder iz AuthConfig
    //da mozemo u saveUser da sacuvamo enkriptovan password

    public Integer saveUser(UserDTO userDTO){
        userDTO.setPassword(authConfig.passwordEncoder.encode(userDTO.getPassword()));
        String jwt = jwtService.generateToken(userDTO.getUsername(), userDTO.getRole());
        Integer newUserId = userService.create(userDTO);
        User savedUser = userRepository
                .getUserByUsername(userDTO.getUsername()).get();
        Token token = saveUserToken(jwt, savedUser);
        return newUserId;
    }

    private void revokeAllUserTokens(User user){
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if(validUserTokens.isEmpty()){
            return;
        }
        validUserTokens.forEach(t ->{
            t.setExpired(true);
            t.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    private Token saveUserToken(String jwt, User savedUser) {
        Token token = Token.builder()
                .user(savedUser)
                .token(jwt)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        System.out.println("token "+ token);
        tokenRepository.save(token);
        return token;
    }

    public String encodePassword(String password){
        return authConfig.passwordEncoder.encode(password);
    }

    public String generateToken(String username, Role role){

        String jwt = jwtService.generateToken(username, role);
        User user= userRepository.getUserByUsername(username).get();

        //da obrisemo sve prethodno generisane tokene
        // za korisnika kada se loguje
        revokeAllUserTokens(user);
        saveUserToken(jwt, user);
        return jwt;
    }

    public void validateToken(String token){
        jwtService.validateToken(token);
    }

    public UserDTO getUserByUsername(String username) {
        return userService.getUserByUsername(username);
    }

}
