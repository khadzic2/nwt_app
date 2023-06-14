package ba.unsa.etf.nwt.itemcart_service.DTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

@Data
@Getter
@Setter
public class ItemCartDTO {
    private Integer selectedSpecificationsId;
    private Integer cartId;
    private Integer itemId;

    private Integer orderId;

    public ItemCartDTO() {
    }

    public ItemCartDTO(Integer cartId, Integer itemId, Integer selectedSpecificationsId, Integer orderId) {
        this.cartId = cartId;
        this.itemId = itemId;
        this.selectedSpecificationsId = selectedSpecificationsId;
        this.orderId = orderId;
    }
}