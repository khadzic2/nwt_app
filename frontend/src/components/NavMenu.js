import React, {Component} from "react";
import {NavLink} from "react-router-dom";
// import "../style/NavMenu.css"
class NavMenu extends Component{
    render() {
        return (
            <nav style={{"--bs-bg-opacity": 1,"background-color": "rgba(var(--bs-light-rgb),var(--bs-bg-opacity))","box-shadow": "0 0 10px 7px rgba(0,0,0,0.5)"}}
                 className="navbar navbar-expand-lg py-2 sticky-top">

                <div className="container">
                    <NavLink className="navbar-brand fw-bold fs-4 px-2" to="/"><i>HomeHarmony</i></NavLink>

                    <div className="navbar-collapse" id="navbarSupportedContent">
                        <ul className="navbar-nav m-auto my-2 text-center">
                            <li>
                                <NavLink className="nav-link" to="/">Početna </NavLink>
                            </li>
                            <li>
                                <NavLink className="nav-link" to="/proizvodi">Proizvodi</NavLink>
                            </li>
                            <li>
                                <NavLink className="nav-link" to="/o-nama">O nama</NavLink>
                            </li>
                        </ul>
                        <div className="buttons text-center">
                            <NavLink to="/prijava" className="btn btn-outline-dark m-2"><i className="fa fa-sign-in mr-1"></i> Prijava</NavLink>
                            <NavLink to="/registracija" className="btn btn-outline-dark m-2"><i className="fa fa-user-plus mr-1"></i> Registracija</NavLink>
                        </div>
                    </div>
                </div>

            </nav>
        );
    }
}

export default NavMenu;