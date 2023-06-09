package ba.unsa.etf.nwt.item_service.DTO;

import ba.unsa.etf.nwt.item_service.Model.*;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Data
@Getter
@Setter
public class ItemDTO {
    private Integer id;
    @NotNull(message = "Name of item can't be null")
    private String name;
    private String description;
    private Double price;

    @NotNull(message="Status can't be null!")
    @Enumerated(EnumType.STRING)
    private Item.StatusType status;
    private Integer manufacturingdays;
    private boolean compared;
    private Image image;
    private Specifications specifications;
    private Stock stock;
    private ItemCategory itemCategory;


    public ItemDTO() {

    }

    public ItemDTO(String name, String description, Double price, Item.StatusType status, Integer manufacturingdays, boolean compared, Image image) {
        this.name = name;
        this.description = description;
        this.price=price;
        this.status = status;
        this.manufacturingdays=manufacturingdays;
        this.compared=compared;
        this.image=image;
    }

    public ItemDTO(Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.description = item.getDescription();
        this.price=item.getPrice();
        this.status = item.getStatus();
        this.manufacturingdays=item.getManufacturingdays();
        this.compared=item.isCompared();
        this.image=item.getImage();
    }
}