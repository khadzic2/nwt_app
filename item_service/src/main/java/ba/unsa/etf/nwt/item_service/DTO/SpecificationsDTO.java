package ba.unsa.etf.nwt.item_service.DTO;

import ba.unsa.etf.nwt.item_service.Model.Item;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Data
@Getter
@Setter
public class SpecificationsDTO {
    private Integer id;
    @NotNull(message="Colors can't be null!")
    private String colors;
    @NotNull(message="Height can't be null!")
    private String height;

    @NotNull(message="width Can't be null!")
    private String width;

    @NotNull(message="depth Can't be null!")
    private String depth;

    @NotNull(message="material Can't be null!")
    private String material;


    public SpecificationsDTO(){
    }

    public SpecificationsDTO(String colors, String height, String width, String depth, String material) {
        this.colors = colors;
        this.height = height;
        this.width = width;
        this.depth = depth;
        this.material = material;
    }
}