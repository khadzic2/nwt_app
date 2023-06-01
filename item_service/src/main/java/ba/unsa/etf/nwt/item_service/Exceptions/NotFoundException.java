package ba.unsa.etf.nwt.item_service.Exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(Integer id) {
        super("Could not find item with id: " + id);
    }
}
