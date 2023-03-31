package ba.unsa.etf.nwt.order_service.model;

import jakarta.persistence.*;

@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer userId;

    private Integer itemId;

    @OneToOne
    @JoinColumn(name = "date_id")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "state_id")
    private State state;

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

    public Integer getUserId() {
        return userId;
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
}
