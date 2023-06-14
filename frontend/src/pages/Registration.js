import {Component} from "react";
import NavMenu from "../components/NavMenu";
import {Link} from "react-router-dom";

class Registration extends Component{
    onSubmit = async event => {
        event.preventDefault();
    }
    render() {
        return(
            <>
                <NavMenu/>
                <div className="container my-3 py-3">
                    <h1 className="text-center">Registracija</h1>
                    <hr/>
                    <div className="row my-4 h-100">
                        <div className="col-md-4 col-lg-4 col-sm-8 mx-auto">
                            <form onSubmit={this.onSubmit}>
                                <div className="form my-3">
                                    <label htmlFor="punoIme">Ime i prezime</label>
                                    <input
                                        type="text"
                                        className="form-control"
                                        id="Naziv"
                                        placeholder="ime i prezime"
                                    />
                                </div>
                                {/*<div className="form my-3">*/}
                                {/*    <label htmlFor="Prezime">Prezime</label>*/}
                                {/*    <input*/}
                                {/*        type="text"*/}
                                {/*        className="form-control"*/}
                                {/*        id="Prezime"*/}
                                {/*        placeholder="prezime"*/}
                                {/*    />*/}
                                {/*</div>*/}
                                <div className="form my-3">
                                    <label htmlFor="Email">Email address</label>
                                    <input
                                        type="email"
                                        className="form-control"
                                        id="Email"
                                        placeholder="ime@example.com"
                                    />
                                </div>
                                <div className="form my-3">
                                    <label htmlFor="KorisnickoIme">Korisničko ime</label>
                                    <input
                                        type="text"
                                        className="form-control"
                                        id="KorisnickoIme"
                                        placeholder="korisničko ime"
                                    />
                                </div>
                                <div className="form  my-3">
                                    <label htmlFor="Šifra">Šifra</label>
                                    <input
                                        type="password"
                                        className="form-control"
                                        id="Šifra"
                                        placeholder="šifra"
                                    />
                                </div>
                                <div className="my-3">
                                    <p>Posjedujete profil? <Link to="/prijava" className="text-decoration-underline text-black">Prijava</Link></p>
                                </div>
                                <div className="text-center">
                                    <button className="my-2 mx-auto btn btn-dark" type="submit">
                                        Registracija
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </>
        );
    }
}

export default Registration;