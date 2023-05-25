package ba.unsa.etf.nwt.user_service.exception;

public class NotAllowedRequest extends RuntimeException{
    public NotAllowedRequest(String message) {
        super(message);
    }
}
