package ba.unsa.etf.nwt.item_service.Model;

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

    @OneToMany(mappedBy = "item")
    private List<Specifications> specifications;

    @OneToMany(mappedBy = "item")
    private List<ItemOrders> itemOrders;


    public Item() {
    }
    public Item(String name, String description, Double price, StatusType status, Integer manufacturingdays) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price=price;
        this.status = status;
        this.manufacturingdays=manufacturingdays;
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


}
