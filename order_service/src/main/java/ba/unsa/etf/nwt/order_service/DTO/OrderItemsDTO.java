package ba.unsa.etf.nwt.order_service.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class OrderItemsDTO {
    private Integer id;

    @NotNull(message = "Ordered item can't be null")
    private Integer itemId;

    @NotNull(message = "Orders can't be null")
    private Integer orderId;

    public OrderItemsDTO(Integer itemId, Integer orderId) {
        this.itemId = itemId;
        this.orderId = orderId;
    }

    public OrderItemsDTO() {
    }
}
