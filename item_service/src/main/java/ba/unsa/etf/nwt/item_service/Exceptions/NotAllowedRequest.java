package ba.unsa.etf.nwt.item_service.Exceptions;

public class NotAllowedRequest extends RuntimeException{
    public NotAllowedRequest(String message) {
        super(message);
    }
}