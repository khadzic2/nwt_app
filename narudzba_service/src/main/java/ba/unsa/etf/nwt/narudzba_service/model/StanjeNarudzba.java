package ba.unsa.etf.nwt.narudzba_service.model;

import jakarta.persistence.*;

@Entity
public class StanjeNarudzba {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne(mappedBy = "stanje")
    private Narudzba narudzba;

    @ManyToOne
    @JoinColumn(name = "stanje_id")
    private Stanje stanje;

    public StanjeNarudzba() {
    }

    public StanjeNarudzba(Stanje stanje) {
        this.stanje = stanje;
    }

    public Integer getId() {
        return id;
    }

    public Narudzba getNarudzbaId() {
        return narudzba;
    }

    public Stanje getStanje() {
        return stanje;
    }
}
