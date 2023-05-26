package ba.unsa.etf.nwt.item_service.Model;

import jakarta.persistence.*;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import ba.unsa.etf.nwt.item_service.Model.Item;

@Entity
public class Stock {

    @NotNull(message="Can't be null!")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToMany(mappedBy = "id")
    private List<Item> items;
    private Integer amount;

    public Stock() {
    }
    public Stock(Item itemId, Integer amount) {
        this.amount=amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }
    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
