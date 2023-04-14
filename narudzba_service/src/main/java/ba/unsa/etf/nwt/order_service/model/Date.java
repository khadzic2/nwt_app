package ba.unsa.etf.nwt.order_service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Date {
    @NotNull(message = "Can't be null!")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private LocalDate deliveryDate;

    private LocalDate delayDate;

    @JsonIgnore
    @OneToOne(mappedBy = "date")
    private Orders orders;

    public Date() {
    }

    public Date(LocalDate deliveryDate, LocalDate delayDate) {
        this.deliveryDate = deliveryDate;
        this.delayDate = delayDate;
    }

    public Integer getId() {
        return id;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public LocalDate getDelayDate() {
        return delayDate;
    }

    public void setDelayDate(LocalDate delayDate) {
        this.delayDate = delayDate;
    }

    public Orders getOrder() {
        return orders;
    }

    public void setOrder(Orders orders) {
        this.orders = orders;
    }
}
