package ba.unsa.etf.nwt.item_service.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ItemCategory {
    @NotNull(message = "Can't be null!")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String category_name;


    @OneToMany(mappedBy = "itemCategory")
    @JsonIgnoreProperties("itemCategory")
    private List<Item> items;

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

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}