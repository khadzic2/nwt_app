package ba.unsa.etf.nwt.order_service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotBlank(message = "User's first name can't be null or blank")
    @Size(min = 3, message = "Length of user's first name must be at least 3")
    private String firstName;
    @NotBlank(message = "Last name can't be null or blank")
    @Size(min = 3, message = "Length of user's last name must be at least 3")
    private String lastName;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Orders> order;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public User() {
    }

    public Integer getId() {
        return id;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Orders> getOrder() {
        return order;
    }

    public void setOrder(List<Orders> order) {
        this.order = order;
    }
}
