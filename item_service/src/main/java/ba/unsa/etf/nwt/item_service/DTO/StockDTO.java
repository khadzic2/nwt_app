package ba.unsa.etf.nwt.item_service.DTO;

import ba.unsa.etf.nwt.item_service.Model.Item;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class StockDTO {
    private Integer id;
    private List<Item> items;
    private Integer amount;


    public StockDTO(Integer amount) {
        this.amount=amount;
    }

    public StockDTO() {
    }
}