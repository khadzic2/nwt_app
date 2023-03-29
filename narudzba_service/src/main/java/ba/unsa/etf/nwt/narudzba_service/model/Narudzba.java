package ba.unsa.etf.nwt.narudzba_service.model;

import jakarta.persistence.*;

@Entity
public class Narudzba {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer korisnikId;

    private Integer artiklId;

    @OneToOne
    @JoinColumn(name = "datum_id")
    private Datum datum;

    @OneToOne
    @JoinColumn(name = "stanje_id")
    private StanjeNarudzba stanje;

    public Narudzba(){}

    public Narudzba(Integer korisnikId, Integer artiklId, Datum datum,StanjeNarudzba stanje) {
        this.korisnikId = korisnikId;
        this.artiklId = artiklId;
        this.datum = datum;
        this.stanje = stanje;
    }

    public Integer getId() {
        return id;
    }

    public Integer getKorisnikId() {
        return korisnikId;
    }

    public Integer getArtiklId() {
        return artiklId;
    }

    public Datum getDatum() {
        return datum;
    }
    public StanjeNarudzba getStanje(){
        return stanje;
    }
}
