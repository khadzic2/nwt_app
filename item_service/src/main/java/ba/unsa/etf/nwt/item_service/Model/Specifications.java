package ba.unsa.etf.nwt.item_service.Model;

import jakarta.persistence.*;

import javax.validation.constraints.NotNull;


@Entity
public class Specifications {
    @NotNull(message="Can't be null!")
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String specifications;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "selectedSpecifications_id")
    private SelectedSpecifications selectedSpecifications;

    public Specifications(){
    }

    public Integer getId() {
        return id;
    }
    public Item getItem(){ return item;}
    public SelectedSpecifications getSelectedSpecifications() {
        return selectedSpecifications;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }
}
