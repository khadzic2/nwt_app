package ba.unsa.etf.nwt.itemcart_service.messaging;

import ba.unsa.etf.nwt.itemcart_service.DTO.OrdersDTO;

public interface Publisher<T extends OrdersDTO> {
    void send(T data);
}
