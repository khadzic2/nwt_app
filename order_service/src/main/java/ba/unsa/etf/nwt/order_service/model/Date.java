package ba.unsa.etf.nwt.order_service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Date {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull(message = "Delivery date can't be null")
    @FutureOrPresent(message = "Delivery date can't be in past")
    private LocalDate deliveryDate;
    @FutureOrPresent(message = "Delay date can't be in past")
    private LocalDate delayDate;

    @JsonIgnore
    @OneToOne(mappedBy = "date",fetch = FetchType.LAZY)
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