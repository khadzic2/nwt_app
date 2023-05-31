package ba.unsa.etf.nwt.order_service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotNull;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull(message = "Ordered item can't be null")
    private Integer itemOrderId;
    @NotNull(message = "Date of order can't be null")
    @JsonIgnoreProperties("order")
    @OneToOne
    @JoinColumn(name = "date_id")
    private Date date;
    @NotNull(message = "State of order can't be null")
    @JsonIgnoreProperties("order")
    @ManyToOne
    @JoinColumn(name = "state_id")
    private State state;
    @NotNull(message = "User of order can't be null")
    private Integer userId;

    public Orders(){}

    public Orders(Integer userId, Integer itemOrderId, Date date, State state) {
        this.userId = userId;
        this.itemOrderId = itemOrderId;
        this.date = date;
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUser() {
        return userId;
    }

    public Integer getItemOrderId() {
        return itemOrderId;
    }

    public Date getDate() {
        return date;
    }
    public State getState(){
        return state;
    }

    public void setUser(Integer userId) {
        this.userId = userId;
    }

    public void setItemOrderId(Integer itemId) {
        this.itemOrderId = itemId;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setState(State state) {
        this.state = state;
    }
}
