package ba.unsa.etf.nwt.item_service.Model;

import jakarta.persistence.*;

import javax.validation.constraints.NotNull;

@Entity
public class ItemOrders {
    @NotNull(message="Can't be null!")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    public ItemOrders() {
    }
    public Integer getId() {
        return id;
    }

    public Item getItem () {
        return item;
    }

    public void setItem(ItemOrders itemOrders) {
        this.item = item;
    }
}
