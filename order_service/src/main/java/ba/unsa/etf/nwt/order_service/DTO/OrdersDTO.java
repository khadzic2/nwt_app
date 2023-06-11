package ba.unsa.etf.nwt.order_service.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class OrdersDTO {
    private Integer id;
    @NotNull(message = "Date of order can't be null")
    private Integer dateId;
    @NotNull(message = "State of order can't be null")
    private Integer stateId;
    @NotNull(message = "User of order can't be null")
    private Integer userId;

    public OrdersDTO() {
    }

    public OrdersDTO(Integer dateId, Integer stateId, Integer userId) {
        this.dateId = dateId;
        this.stateId = stateId;
        this.userId = userId;
    }
}
