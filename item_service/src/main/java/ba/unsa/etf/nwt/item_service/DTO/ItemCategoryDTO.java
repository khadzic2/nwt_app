package ba.unsa.etf.nwt.item_service.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ItemCategoryDTO {
    private Integer id;
    private String room;
    private String typeOfItem;

    public ItemCategoryDTO(String room,String typeOfItem) {
        this.room = room;
        this.typeOfItem=typeOfItem;
    }

    public ItemCategoryDTO() {
    }
}