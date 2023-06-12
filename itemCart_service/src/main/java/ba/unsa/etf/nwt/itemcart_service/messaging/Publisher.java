package ba.unsa.etf.nwt.itemcart_service.messaging;

public interface Publisher<T extends Integer> {
    void send(T data);
}
