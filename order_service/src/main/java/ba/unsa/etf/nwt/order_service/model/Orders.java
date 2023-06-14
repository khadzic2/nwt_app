package ba.unsa.etf.nwt.order_service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull(message = "Date of order can't be null")
    @JsonIgnoreProperties("order")
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "date_id")
    private Date date;
    @NotNull(message = "State of order can't be null")
    @JsonIgnoreProperties("order")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "state_id")
    private State state;
    @NotNull(message = "User of order can't be null")
    private Integer userId;
    private Boolean deleted;
    @JsonIgnoreProperties("orders")
    @OneToMany(mappedBy = "orders",fetch = FetchType.EAGER)
    private List<OrderItems> items;

    public Orders(){
        this.deleted = false;
    }

    public Orders(Integer userId, Date date, State state, Boolean deleted) {
        this.userId = userId;
        this.date = date;
        this.state = state;
        this.deleted = deleted;
    }

    public Integer getId() {
        return id;
    }

    public List<OrderItems> getItems() {
        return items;
    }

    public Date getDate() {
        return date;
    }
    public State getState(){
        return state;
    }

    public Integer getUserId() {
        return userId;
    }

    public Boolean getDeleted() {return deleted;}

    public void setItems(List<OrderItems> items) {
        this.items = items;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setDeleted(Boolean deleted) {this.deleted = deleted;}
}
