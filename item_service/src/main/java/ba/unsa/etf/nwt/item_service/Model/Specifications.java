package ba.unsa.etf.nwt.item_service.Model;

import jakarta.persistence.*;

import javax.validation.constraints.NotNull;


@Entity
public class Specifications {
    @NotNull(message="Can't be null!")
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String color;
    private Double weight;
    private String dimensions;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    public Specifications(){
    }

    public Integer getId() {
        return id;
    }
    public Item getItem(){ return item;}

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }
}
