package ba.unsa.etf.nwt.narudzba_service.model;

import jakarta.persistence.*;

@Entity
public class Narudzba {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer korisnikId;

    private Integer artiklId;

    private Integer datum;

    private Integer snID;

    public Integer getId() {
        return id;
    }

    public Integer getKorisnikId() {
        return korisnikId;
    }

    public Integer getArtiklId() {
        return artiklId;
    }

    public Integer getDatum() {
        return datum;
    }

    public Integer getSnID() {
        return snID;
    }
}
