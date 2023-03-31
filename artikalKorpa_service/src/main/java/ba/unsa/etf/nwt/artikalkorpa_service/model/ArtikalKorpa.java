package ba.unsa.etf.nwt.artikalkorpa_service.model;

import jakarta.persistence.*;

@Entity
public class ArtikalKorpa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "artikal_id")
    private Artikal artikal;

    @ManyToOne
    @JoinColumn(name = "korpa_id")
    private Korpa korpa;
    public ArtikalKorpa(){}

    public Integer getId() {
        return id;
    }

    public Artikal getArtikal() {
        return artikal;
    }

    public void setArtikal(Artikal artikal) {
        this.artikal = artikal;
    }

    public Korpa getKorpa() {
        return korpa;
    }

    public void setKorpa(Korpa korpa) {
        this.korpa = korpa;
    }
}
