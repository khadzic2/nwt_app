package ba.unsa.etf.nwt.itemcart_service.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

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
}