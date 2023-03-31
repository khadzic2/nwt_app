package ba.unsa.etf.nwt.order_service.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Date {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private LocalDate deliveryDate;

    private LocalDate delayDate;

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

    public void setDeliveryDate(LocalDate datumIsporuke) {
        this.deliveryDate = datumIsporuke;
    }

    public LocalDate getDelayDate() {
        return delayDate;
    }

    public void setDelayDate(LocalDate datumOdgode) {
        this.delayDate = datumOdgode;
    }

    public Orders getOrder() {
        return orders;
    }

    public void setOrder(Orders orders) {
        this.orders = orders;
    }
}
