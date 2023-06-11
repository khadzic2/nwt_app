package ba.unsa.etf.nwt.itemcart_service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Collection;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToMany(mappedBy = "cart")
    @JsonIgnoreProperties("cart")
    private Collection<ItemCart> itemCarts;

    public Collection<ItemCart> getItemCarts() {
        return itemCarts;
    }

    public void setItemCarts(Collection<ItemCart> itemCarts) {
        this.itemCarts = itemCarts;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @NotNull(message="User id can't be null!")
    private Integer userId;

    public Integer getId() {
        return id;
    }

    public Cart() {
    }

    public Cart(Collection<ItemCart> itemCarts, Integer userId) {
        this.itemCarts = itemCarts;
        this.userId = userId;
    }
}
