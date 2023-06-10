package ba.unsa.etf.nwt.item_service.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotEmpty(message = "Name is required!")
    @Size(max = 30, message = "Name can't be longer than 30 characters!")
    private String name;
    private String description;
    private Double price;
    @NotNull(message="Manufacturing days can't be null!")
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("items")
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("items")
    @JoinColumn(name = "itemcategory_id")
    private ItemCategory itemCategory;


    public Item() {
        compared=false;
    }
    public Item(String name, String description, Double price, Integer manufacturingdays,
                Specifications specifications, Stock stock, boolean compared, Image image, ItemCategory category) {
        this.name = name;
        this.description = description;
        this.price=price;
        this.manufacturingdays=manufacturingdays;
        this.compared=compared;
        this.stock=stock;
        this.specifications=specifications;
        this.image=image;
        this.itemCategory=category;
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

    public Specifications getSpecifications() {
        return specifications;
    }

    public void setSpecifications(Specifications specifications) {
        this.specifications = specifications;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public ItemCategory getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(ItemCategory itemCategory) {
        this.itemCategory = itemCategory;
    }
}
