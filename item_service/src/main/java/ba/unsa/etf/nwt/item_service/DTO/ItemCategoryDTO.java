package ba.unsa.etf.nwt.item_service.DTO;

import ba.unsa.etf.nwt.item_service.Model.Item;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class ItemCategoryDTO {
    private Integer id;
    private String category_name;
    private List<Item> items;


    public ItemCategoryDTO(String category_name) {
        this.category_name=category_name;
    }

    public ItemCategoryDTO() {
    }
}