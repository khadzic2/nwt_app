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

    private String room;

    private String typeOfItem;
    @OneToMany(mappedBy = "itemCategory")
    @JsonIgnoreProperties("itemCategory")
    private List<Item> items;

    public ItemCategory() {
    }

    public ItemCategory(String room, String typeOfItem) {
        this.room = room;
        this.typeOfItem = typeOfItem;
    }

    public Integer getId() {
        return id;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String category_name) {
        this.room = category_name;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getTypeOfItem() {
        return typeOfItem;
    }

    public void setTypeOfItem(String typeOfItem) {
        this.typeOfItem = typeOfItem;
    }
}