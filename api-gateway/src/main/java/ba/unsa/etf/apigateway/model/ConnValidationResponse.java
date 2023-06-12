package ba.unsa.etf.apigateway.model;

import java.util.List;

public class ConnValidationResponse {
    private List<UserDTO> users;

    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }
}

//@Getter
//@ToString
//@NoArgsConstructor
//public class ConnValidationResponse {
//    private String status;
//    private boolean isAuthenticated;
//    private String methodType;
//    private String username;
//    private String token;
//    private List<Authorities> authorities;
//}