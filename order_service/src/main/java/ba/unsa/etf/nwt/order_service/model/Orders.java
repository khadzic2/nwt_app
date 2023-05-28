package ba.unsa.etf.nwt.order_service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
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
    @NotNull(message = "Ordered item can't be null")
    private Integer itemId;
    public Orders(){}

    public Orders(Integer userId, Integer itemId, Date date, State state) {
        this.userId = userId;
        this.itemId = itemId;
        this.date = date;
        this.state = state;
    }

    public Integer getId() {
        return id;
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

    public Integer getUserId() {
        return userId;
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

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
