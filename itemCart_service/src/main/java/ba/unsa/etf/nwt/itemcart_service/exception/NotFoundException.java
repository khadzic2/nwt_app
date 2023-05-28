package ba.unsa.etf.nwt.itemcart_service.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Integer id, String value) {
        super("Not found " + value + " by id " + id);
    }
}
