package ba.unsa.etf.nwt.item_service.DTO;

import ba.unsa.etf.nwt.item_service.Model.Item;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ImageDTO {
    private Integer id;
    private String name;
    private String type;
    private byte[] imageData;


    public ImageDTO(String name, String type, byte[] imageData) {
        this.name = name;
        this.type = type;
        this.imageData = imageData;
    }

    public ImageDTO() {
    }
}