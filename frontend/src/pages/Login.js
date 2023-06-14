import {Component} from "react";
import NavMenu from "../components/NavMenu";
import {Link} from "react-router-dom";
// import UserService from "../services/UserService";
import AuthService from "../services/AuthService";

class Login extends Component{
    constructor(props) {
        super(props);
        this.state={
            username:"",
            password:""
        }
    }
    handleUsernameChange = event => {
        this.setState({username: event.target.value, password: this.state.password});
    }

    handlePasswordChange = event => {
        this.setState({username: this.state.username, password: event.target.value});
    }
    onSubmit = async event => {
        AuthService.login(this.state.username,this.state.password);
        event.preventDefault();
        window.location.href='/';
    }
    render() {
        return(
            <>
                <NavMenu/>
                <div className="container my-3 py-3">
                    <h1 className="text-center">Prijava</h1>
                    <hr/>
                    <div className="row my-4 h-100">
                        <div className="col-md-4 col-lg-4 col-sm-8 mx-auto">
                            <form onSubmit={this.onSubmit}>
                                <div className="my-3">
                                    <label htmlFor="display-4">Korisničko ime</label>
                                    <input type="text" className="form-control" id="floatingInput" placeholder="korisničko ime" onChange={this.handleUsernameChange}/>
                                </div>
                                <div className="my-3">
                                    <label htmlFor="floatingPassword display-4">Šifra</label>
                                    <input type="password" className="form-control" id="floatingPassword" placeholder="šifra" onChange={this.handlePasswordChange}/>
                                </div>
                                <div className="my-3">
                                    <p><Link to="/registracija" className="text-decoration-underline text-black">Registracija</Link> </p>
                                </div>
                                <div className="text-center">
                                    <button className="my-2 mx-auto btn btn-dark" type="submit">
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