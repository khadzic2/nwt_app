package ba.unsa.etf.nwt.narudzba_service.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Stanje {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String stanje;
    @OneToMany(mappedBy = "stanje")
    private List<StanjeNarudzba> stanja_narudzbe;

    public Stanje(){}
    public Stanje(String stanje) {
        this.stanje = stanje;
    }

    public Integer getId() {
        return id;
    }

    public String getStanje() {
        return stanje;
    }

    public void setStanje(String stanje) {
        this.stanje = stanje;
    }

    public List<StanjeNarudzba> getStanja_narudzbe() {
        return stanja_narudzbe;
    }

    public void setStanja_narudzbe(List<StanjeNarudzba> stanja_narudzbe) {
        this.stanja_narudzbe = stanja_narudzbe;
    }
}
