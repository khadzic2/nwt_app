package ba.unsa.etf.nwt.artikalkorpa_service.model;

import jakarta.persistence.*;

@Entity
public class Korisnik {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne(mappedBy = "korpa_id")
    private Korpa korpa;


    public Korisnik() {
    }

    public Integer getId() {
        return id;
    }

}
