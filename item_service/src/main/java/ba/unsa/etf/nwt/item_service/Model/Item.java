package ba.unsa.etf.nwt.item_service.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Item {

    public enum StatusType{AVAILABLE, NOT_AVAILABLE}
    //AVAILABLE - artikal je na stanju
    //NOT_AVAILABLE - artikal trenutno nije na stanju i nije dostupan

    @NotNull(message="Can't be null!")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotEmpty(message = "Name is required!")
    @Size(max = 30, message = "Name can't be longer than 30 characters!")
    private String name;
    private String description;
    private Double price;
    @NotNull(message="Status can't be null!")
    @Enumerated(EnumType.STRING)
    private StatusType status;
    private Integer manufacturingdays;

    private boolean compared;

    @OneToOne (fetch = FetchType.EAGER)
    @JsonIgnoreProperties("item")
    @JoinColumn(name = "image_id")
    private Image image;
    @OneToOne
    @JsonIgnoreProperties("item")
    @JoinColumn(name = "specifications_id")
    private Specifications specifications;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("items")
    private Stock stock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("items")
    private ItemCategory itemCategory;


    public Item() {
        compared=false;
    }
    public Item(String name, String description, Double price, StatusType status, Integer manufacturingdays, boolean compared, Image image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price=price;
        this.status = status;
        this.manufacturingdays=manufacturingdays;
        this.compared=compared;
        this.image=image;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getManufacturingdays() {
        return manufacturingdays;
    }
    public void setManufacturingdays(Integer manufacturingdays) {
        this.manufacturingdays = manufacturingdays;
    }

    public boolean isCompared() {
        return compared;
    }

    public void setCompared(boolean compared) {
        this.compared = compared;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public ItemCategory getCategory() {
        return itemCategory;
    }

    public void setCategory(ItemCategory itemCategory) {
        this.itemCategory = itemCategory;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

}
