package ba.unsa.etf.nwt.order_service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import javax.validation.constraints.NotNull;

@Entity
public class Orders {
    @NotNull(message = "Can't be null!")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer itemId;

    @JsonIgnoreProperties("order")
    @OneToOne
    @JoinColumn(name = "date_id")
    private Date date;
    @JsonIgnoreProperties("order")
    @ManyToOne
    @JoinColumn(name = "state_id")
    private State state;
    @JsonIgnoreProperties("order")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Orders(){}

    public Orders(User user, Integer itemId, Date date, State state) {
        this.user = user;
        this.itemId = itemId;
        this.date = date;
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Integer getItemId() {
        return itemId;
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

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setState(State state) {
        this.state = state;
    }
}
