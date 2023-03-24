package ba.unsa.etf.nwt.narudzba_service.model;

import jakarta.persistence.*;

@Entity
public class StanjeNarudzba {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer narudzbaId;

    private Integer stanjeId;

    public Integer getId() {
        return id;
    }

    public Integer getNarudzbaId() {
        return narudzbaId;
    }

    public Integer getStanjeId() {
        return stanjeId;
    }
}
