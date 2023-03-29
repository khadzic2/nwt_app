package ba.unsa.etf.nwt.narudzba_service.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Datum {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private LocalDate datumIsporuke;

    private LocalDate datumOdgode;

    @OneToOne(mappedBy = "datum")
    private Narudzba narudzba;

    public Datum() {
    }

    public Datum(LocalDate datumIsporuke, LocalDate datumOdgode) {
        this.datumIsporuke = datumIsporuke;
        this.datumOdgode = datumOdgode;
    }

    public Integer getId() {
        return id;
    }

    public LocalDate getDatumIsporuke() {
        return datumIsporuke;
    }

    public void setDatumIsporuke(LocalDate datumIsporuke) {
        this.datumIsporuke = datumIsporuke;
    }

    public LocalDate getDatumOdgode() {
        return datumOdgode;
    }

    public void setDatumOdgode(LocalDate datumOdgode) {
        this.datumOdgode = datumOdgode;
    }

    public Narudzba getNarudzba() {
        return narudzba;
    }

    public void setNarudzba(Narudzba narudzba) {
        this.narudzba = narudzba;
    }
}
