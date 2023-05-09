package ba.unsa.etf.nwt.item_service.Model;

import jakarta.persistence.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class SelectedSpecifications {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull(message="Can't be null!")
    private String selected;

    @OneToMany(mappedBy = "selectedSpecifications")
    private List<Specifications> specifications;

    public SelectedSpecifications() {
    }

    public Integer getId() {
        return id;
    }

    public String getSelected() {
        return selected;
    }


}
