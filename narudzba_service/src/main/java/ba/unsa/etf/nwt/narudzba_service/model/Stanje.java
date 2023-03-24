package ba.unsa.etf.nwt.narudzba_service.model;

import jakarta.persistence.*;

@Entity
public class Stanje {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String stanje;

    private Integer snID;

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

    public Integer getSnID() {
        return snID;
    }
}
