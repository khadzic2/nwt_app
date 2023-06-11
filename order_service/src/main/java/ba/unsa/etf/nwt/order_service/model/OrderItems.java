package ba.unsa.etf.nwt.order_service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull(message = "Ordered item can't be null")
    private Integer itemId;

    @NotNull(message = "Orders can't be null")
    @JsonIgnoreProperties("items")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private Orders orders;

    public OrderItems(Integer itemId, Orders orders) {
        this.itemId = itemId;
        this.orders = orders;
    }

    public OrderItems() {
    }

    public Integer getId() {
        return id;
    }

    public Integer getItemId() {
        return itemId;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }
}
