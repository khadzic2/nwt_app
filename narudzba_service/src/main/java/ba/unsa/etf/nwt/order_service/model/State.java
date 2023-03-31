package ba.unsa.etf.nwt.order_service.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String state;
    @OneToMany(mappedBy = "state")
    private List<Orders> orders;

    public State(){}
    public State(String state) {
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }
}
