package ba.unsa.etf.nwt.artikalkorpa_service.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Korpa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private LocalDate datumDodavanja;

    @OneToMany(mappedBy = "id")
    private List<ArtikalKorpa> artikli;

    @OneToOne
    @JoinColumn(name = "korisnik_id")
    private Korisnik korisnik;

    public Korpa() {
    }

    public Korpa(LocalDate datumDodavanja, List<ArtikalKorpa> artikli) {
        this.datumDodavanja = datumDodavanja;
        this.artikli = artikli;
    }

    public Integer getId() {
        return id;
    }

    public LocalDate getDatumDodavanja() {
        return datumDodavanja;
    }

    public List<ArtikalKorpa> getArtikli() {
        return artikli;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setDatumDodavanja(LocalDate datumDodavanja) {
        this.datumDodavanja = datumDodavanja;
    }

    public void setArtikli(List<ArtikalKorpa> artikli) {
        this.artikli = artikli;
    }
}
