package ba.unsa.etf.nwt.itemcart_service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class SelectedSpecifications {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @OneToOne(mappedBy = "selectedSpecifications")
    @JsonIgnoreProperties("selectedSpecifications")
    private ItemCart itemCart;
    @NotNull(message="color Can't be null!")
    private String color;

    public ItemCart getItemCart() {
        return itemCart;
    }

    public void setItemCart(ItemCart itemCart) {
        this.itemCart = itemCart;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @NotNull(message="height Can't be null!")
    private String height;

    @NotNull(message="width Can't be null!")
    private String width;

    @NotNull(message="depth Can't be null!")
    private String depth;

    @NotNull(message="material Can't be null!")
    private String material;

    public Integer getId() {
        return id;
    }

    public SelectedSpecifications() {
    }

    public SelectedSpecifications(String color, String height, String width, String depth, String material) {
        this.color = color;
        this.height = height;
        this.width = width;
        this.depth = depth;
        this.material = material;
    }

}
