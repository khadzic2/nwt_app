package ba.unsa.etf.nwt.itemcart_service.DTO;

import ba.unsa.etf.nwt.itemcart_service.model.ItemCart;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

@Data
@Getter
@Setter
public class CartDTO {
    private Integer id;
    @NotNull(message="User id can't be null!")
    private Integer userId;

    public CartDTO() {
    }

    public CartDTO(Collection<ItemCart> itemCarts, Integer userId) {
        this.userId = userId;
    }
}