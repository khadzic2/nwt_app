package ba.unsa.etf.nwt.item_service.Exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(Integer id, String value) {
        super("Not found " + value + " by id " + id);
    }
}
