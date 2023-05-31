package ba.unsa.etf.nwt.item_service.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import javax.validation.constraints.NotNull;


@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Specifications {
    @NotNull(message="Can't be null!")
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
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


    @OneToOne(mappedBy = "specifications")
    @JsonIgnoreProperties("specifications")
    private Item item;

    public Specifications(){
    }

    public Integer getId() {
        return id;
    }
    public Item getItem(){ return item;}


    public String getColors() {
        return colors;
    }

    public void setColors(String colors) {
        this.colors = colors;
    }


    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getHeight() {
        return height;
    }

    public String getWidth() {
        return width;
    }

    public String getDepth() {
        return depth;
    }


    public void setHeight(String height) {
        this.height = height;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
