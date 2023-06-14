import React, {Component} from "react";
import NavMenu from "../components/NavMenu";

class AboutUs extends Component{
    render() {
        return(
            <>
                <NavMenu/>
                <div className="container my-3 py-3">
                    <h1 className="text-center">Dobrodošli u HomeHarmony!</h1>
                    <hr />
                    <p className="lead text-center">
                        Sa više od 15 godina iskustva u industriji namještaja, HomeHarmony je vaša pouzdana destinacija za sve vaše kućne potrebe. Ponosni smo na pružanje visokokvalitetnog namještaja i izuzetne korisničke usluge kako bismo stvorili skladan životni prostor za vas.<br/>
                        U HomeHarmony-u zadovoljstvo kupaca je naš glavni prioritet. Tokom godina, opslužili smo više od 1000 zadovoljnih klijenata, pomažući im da svoje kuće preobraze u domove koje vole. Ono što nas izdvaja je naša posvećenost pružanju širokog spektra funkcionalnosti koje će zadovoljiti vaše jedinstvene potrebe.
                        Možete s lakoćom pretraživati i kupovati na našoj web stranici prilagođenoj korisnicima, dajući vam slobodu da pregledate našu opsežnu kolekciju kad god vam to najviše odgovara. Možete jednostavno filtrirati i tražiti određene komade namještaja, stilove ili materijale, što olakšava pronalaženje savršenog spoja za vaš dom.
                        Nudimo i dodatne usluge kako bismo osigurali Vaše zadovoljstvo. Naš obrazovan i prijateljski tim za korisničku podršku je tu da vam pomogne sa bilo kakvim upitima, preporukama proizvoda ili pomoći oko kupovine. Pružamo detaljne opise proizvoda, slike i dimenzije kako bismo vam pomogli da donesete informirane odluke. Osim toga,
                        nudimo sigurne i pouzdane opcije dostave, osiguravajući da vaš namještaj sigurno i brzo stigne na vaš prag.
                        HomeHarmony nije samo prodavnica namještaja. To je destinacija za stvaranje skladnog kućnog okruženja. Uz naše bogato iskustvo, posvećenu korisničku podršku i raznovrsne mogućnosti namještaja, uvjereni smo da ćete pronaći savršene komade koji će upotpuniti vaš dom iz snova.
                        Hvala vam što ste odabrali HomeHarmony. <br/>Dozvolite nam da unesemo harmoniju u vaš dom!
                    </p>
                    <hr/>
                    <h1 className="text-center">Kontakt</h1>
                    <hr/>
                    <p className="lead text-center">
                        info@homeharmony.com <br/>
                        +387 33 123 123 <br/>
                        +387 62 000 111 <br/>
                        Pon-Sub: 08:00 - 20:00
                        Ned: 10:00 - 16:00
                    </p>
                    <div className="row">
                        <img src="/images/sarajevo.jpg" className="d-block" alt="sarajevo"/>
                    </div>
                </div>
            </>
        );
    }
}

export default AboutUs;