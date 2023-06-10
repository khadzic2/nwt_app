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

    @NotNull(message="Manufacturing days can't be null!")
    private Integer manufacturingdays;
    private boolean compared;
    private Integer imageId;
    private Integer specificationsId;
    private Integer stockId;
    private Integer itemCategoryId;


    public ItemDTO() {
        compared=false;
    }
    public ItemDTO(String name, String description, Double price, Integer manufacturingdays,
                Integer specificationsId, Integer stockId, boolean compared, Integer imageId, Integer categoryId) {
        this.name = name;
        this.description = description;
        this.price=price;
        this.manufacturingdays=manufacturingdays;
        this.compared=compared;
        this.stockId=stockId;
        this.specificationsId=specificationsId;
        this.imageId=imageId;
        this.itemCategoryId=categoryId;
    }

    public ItemDTO(Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.description = item.getDescription();
        this.price=item.getPrice();
        this.manufacturingdays=item.getManufacturingdays();
        this.compared=item.isCompared();
        this.stockId=getStockId();
        this.specificationsId=getSpecificationsId();
        this.imageId=getImageId();
        this.itemCategoryId=getItemCategoryId();
    }
}