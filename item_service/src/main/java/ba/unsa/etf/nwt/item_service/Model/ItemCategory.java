package ba.unsa.etf.nwt.item_service.Model;

import jakarta.persistence.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class ItemCategory {
    @NotNull(message = "Can't be null!")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String category_name;

    @OneToMany(mappedBy = "id")
    private List<Item> item;

    public ItemCategory() {
    }

    public Integer getId() {
        return id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
}