import React, {Component} from "react";
import {NavLink} from "react-router-dom";
import AuthService from "../services/AuthService";
// import "../style/NavMenu.css"
class NavMenu extends Component{
    handleLogOut = event => {
        AuthService.logout();
        event.preventDefault();
        window.location.href ="/";
    }

    createAdminNav(){
        return  <div className="navbar-collapse" id="navbarSupportedContent">
            <ul className="navbar-nav m-auto my-2 text-center">
                <li>
                    <NavLink className="nav-link" to="/">Početna </NavLink>
                </li>
                <li>
                    <NavLink className="nav-link" to="/proizvodi-admin">Proizvodi</NavLink>
                </li>
                <li>
                    <NavLink className="nav-link" to="/narudzbe-admin">Narudzbe</NavLink>
                </li>
                <li>
                    <NavLink className="nav-link" to="/o-nama">O nama</NavLink>
                </li>
            </ul>
            <div className="buttons text-center">
                <NavLink onClick={this.handleLogOut} className="btn btn-outline-dark m-2"><i className="fa fa-sign-out mr-1"></i> Odjava</NavLink>
            </div>
        </div>
    }
    createCustomerNav(){
        return  <div className="navbar-collapse" id="navbarSupportedContent">
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
                <NavLink to="/korpa" className="btn btn-outline-dark m-2"> Korpa </NavLink>
                <NavLink to="/narudzbe" className="btn btn-outline-dark m-2"> Narudzbe </NavLink>
                <NavLink onClick={this.handleLogOut} className="btn btn-outline-dark m-2"><i className="fa fa-sign-out mr-1"></i> Odjava</NavLink>
            </div>
        </div>
    }
    createNotLoggedNav(){
        return <div className="navbar-collapse" id="navbarSupportedContent">
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
    }
    render() {
        return (
            <nav style={{"--bs-bg-opacity": 1,backgroundColor: "rgba(var(--bs-light-rgb),var(--bs-bg-opacity))",boxShadow: "0 0 10px 7px rgba(0,0,0,0.5)"}}
                 className="navbar navbar-expand-lg py-2 sticky-top">

                <div className="container">
                    <NavLink className="navbar-brand fw-bold fs-4 px-2" to="/"><i>HomeHarmony</i></NavLink>
                    {
                        !AuthService.getCurrentUser() && this.createNotLoggedNav()
                    }
                    {
                        AuthService.getCurrentUser() && AuthService.getCurrentUser().role==="customer" && this.createCustomerNav()
                    }
                    {
                        AuthService.getCurrentUser() && AuthService.getCurrentUser().role==="admin" && this.createAdminNav()
                    }
                </div>

            </nav>
        );
    }
}

export default NavMenu;