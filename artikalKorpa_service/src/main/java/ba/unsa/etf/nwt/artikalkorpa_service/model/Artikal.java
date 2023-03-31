package ba.unsa.etf.nwt.artikalkorpa_service.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Artikal {


    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String naziv;

    private String opis;
    public Artikal(){
    }

    public Integer getId() {
        return id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
}
