package ba.unsa.etf.nwt.order_service.exception;

public class NotAllowedRequest extends RuntimeException{
    public NotAllowedRequest(String message) {
        super(message);
    }
}
