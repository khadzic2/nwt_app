package ba.unsa.etf.nwt.user_service.config;

public class UserNotFoundException extends Throwable {
    public UserNotFoundException(String userName_or_password_is_invalid) {
        super(userName_or_password_is_invalid);
    }
}
