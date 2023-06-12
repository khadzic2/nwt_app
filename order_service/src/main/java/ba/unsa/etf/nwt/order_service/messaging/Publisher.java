package ba.unsa.etf.nwt.order_service.messaging;

public interface Publisher<T extends Integer> {
    void send(T data);
}
