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
    @JsonIgnoreProperties("order")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Orders(){}

    public Orders(User user, Integer itemOrderId, Date date, State state) {
        this.user = user;
        this.itemOrderId = itemOrderId;
        this.date = date;
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public User getUser() {
        return user;
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

    public void setUser(User user) {
        this.user = user;
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
