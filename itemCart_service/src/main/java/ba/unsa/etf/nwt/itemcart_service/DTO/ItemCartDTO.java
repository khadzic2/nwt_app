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
    private Integer selectedSpecificationsId;
    private Integer cartId;
    private Integer itemId;

    private Integer orderId;

    private Boolean deleted;


    public ItemCartDTO() {
    }

    public ItemCartDTO(Integer cartId, Integer itemId, Integer orderId, Boolean deleted) {
        this.cartId = cartId;
        this.itemId = itemId;
        this.orderId = orderId;
        this.deleted = deleted;
    }
}