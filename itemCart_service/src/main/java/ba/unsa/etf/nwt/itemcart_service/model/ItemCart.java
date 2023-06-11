package ba.unsa.etf.nwt.itemcart_service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class ItemCart {

    public ItemCart() {
    }

    public ItemCart(Cart cart, Integer itemId, Integer orderId, SelectedSpecifications selectedSpecifications) {
        this.cart = cart;
        this.itemId = itemId;
        this.orderId = orderId;
        this.selectedSpecifications = selectedSpecifications;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull(message="SelectedSpecifications can't be null!")
    @OneToOne
    @JsonIgnoreProperties("itemCart")
    @JoinColumn(name = "selected_specifications_id")
    private SelectedSpecifications selectedSpecifications;

    public Integer getId() {
        return id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("itemCarts")
    private Cart cart;

    public SelectedSpecifications getSelectedSpecifications() {
        return selectedSpecifications;
    }

    public void setSelectedSpecifications(SelectedSpecifications selectedSpecifications) {
        this.selectedSpecifications = selectedSpecifications;
    }

    @NotNull(message="itemId Can't be null!")
    private Integer itemId;

    private Integer orderId;

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
