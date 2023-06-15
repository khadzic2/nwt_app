package ba.unsa.etf.nwt.itemcart_service.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class OrdersDTO {
    private Integer id;

    private Integer dateId;

    private Integer stateId;

    private Integer userId;
    private Boolean deleted;
    public OrdersDTO() {
        this.deleted = false;
    }
    public OrdersDTO(Integer dateId, Integer stateId, Integer userId) {
        this.dateId = dateId;
        this.stateId = stateId;
        this.userId = userId;
        this.deleted = false;
    }
}
