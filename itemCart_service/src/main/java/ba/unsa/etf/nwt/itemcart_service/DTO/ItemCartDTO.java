package ba.unsa.etf.nwt.itemcart_service.DTO;

import ba.unsa.etf.nwt.itemcart_service.model.Cart;
import ba.unsa.etf.nwt.itemcart_service.model.ItemCart;
import ba.unsa.etf.nwt.itemcart_service.model.SelectedSpecifications;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

@Data
@Getter
@Setter
public class ItemCartDTO {
    private Integer id;
    private SelectedSpecifications selectedSpecifications;;
    private Cart cart;
    private Integer itemId;

    private Integer orderId;

    private Boolean deleted;


    public ItemCartDTO() {
    }

    public ItemCartDTO(Cart cart, Integer itemId, Integer orderId, Boolean deleted) {
        this.cart = cart;
        this.itemId = itemId;
        this.orderId = orderId;
        this.deleted = deleted;
    }
}