package ba.unsa.etf.nwt.itemcart_service.DTO;

import ba.unsa.etf.nwt.itemcart_service.model.ItemCart;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class SelectedSpecificationsDTO {
    private Integer id;
    private ItemCart itemCart;
    private String color;
    @NotNull(message="height Can't be null!")
    private String height;

    @NotNull(message="width Can't be null!")
    private String width;

    @NotNull(message="depth Can't be null!")
    private String depth;

    @NotNull(message="material Can't be null!")
    private String material;

    @Min(value = 0,message = "kolicina can't be negative number")
    private Integer kolicina;

    public SelectedSpecificationsDTO() {
    }

    public SelectedSpecificationsDTO(String color, String height, String width, String depth, String material, Integer kolicina) {
        this.color = color;
        this.height = height;
        this.width = width;
        this.depth = depth;
        this.material = material;
        this.kolicina = kolicina;
    }
}