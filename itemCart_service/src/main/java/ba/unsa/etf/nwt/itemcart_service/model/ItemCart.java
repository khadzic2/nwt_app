package ba.unsa.etf.nwt.itemcart_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class ItemCart {

    public ItemCart() {
        this.deleted = false;
    }

    public ItemCart(Cart cart, Integer itemId, Integer orderId, SelectedSpecifications selectedSpecifications) {
        this.cart = cart;
        this.itemId = itemId;
        this.orderId = orderId;
        this.deleted = false;
        this.selectedSpecifications = selectedSpecifications;
    }

    @NotNull(message="Can't be null!")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull(message="Can't be null!")
    @OneToOne
    private SelectedSpecifications selectedSpecifications;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Cart cart;

    public SelectedSpecifications getSelectedSpecifications() {
        return selectedSpecifications;
    }

    public void setSelectedSpecifications(SelectedSpecifications selectedSpecifications) {
        this.selectedSpecifications = selectedSpecifications;
    }

    @NotNull(message="Can't be null!")
    private Integer itemId;

    private Integer orderId;

    private Boolean deleted;

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

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
