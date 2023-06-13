import {Component} from "react";
import NavMenu from "../components/NavMenu";
import {Link} from "react-router-dom";

class Login extends Component{
    render() {
        return(
            <>
                <NavMenu/>
                <div className="container my-3 py-3">
                    <h1 className="text-center">Prijava</h1>
                    <hr/>
                    <div className="row my-4 h-100">
                        <div className="col-md-4 col-lg-4 col-sm-8 mx-auto">
                            <form>
                                <div className="my-3">
                                    <label htmlFor="display-4">Korisničko ime</label>
                                    <input type="text" className="form-control" id="floatingInput" placeholder="korisničko ime"/>
                                </div>
                                <div className="my-3">
                                    <label htmlFor="floatingPassword display-4">Šifra</label>
                                    <input type="password" className="form-control" id="floatingPassword" placeholder="šifra"/>
                                </div>
                                <div className="my-3">
                                    <p><Link to="/registracija" className="text-decoration-underline text-black">Registracija</Link> </p>
                                </div>
                                <div className="text-center">
                                    <button className="my-2 mx-auto btn btn-dark" type="submit" disabled>
                                        Prijavi se
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

export default Login;