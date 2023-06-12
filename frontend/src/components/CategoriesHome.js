import React, {Component} from "react";
import "../style/CategoriesHome.css"
import {NavLink} from "react-router-dom";
class CategoriesHome extends Component{
    render() {
        return(
            <div className="text-center m-4">
                <div className="row align-items-start py-4 fs-3">
                    <div className="col">
                        <i>Opremite svoju sobu najboljim namještajem</i>
                    </div>
                </div>
                <div className="row align-items-start py-4 fs-4">
                    <div className="col">
                        Kategorije
                    </div>
                </div>
                <div className="row align-items-start">
                    <div className="col p-4">
                        <div style={{backgroundColor:"rgba(248, 249, 250,1)",textDecoration:"none"}}
                             className="card">
                            <div className="card-body">
                                <NavLink className="nav-link" to="/proizvodi">Dnevna soba </NavLink>
                            </div>
                        </div>
                    </div>
                    <div className="col p-4">
                        <div style={{backgroundColor:"rgba(248, 249, 250,1)",textDecoration:"none"}}
                             className="card">
                            <div className="card-body">
                                <NavLink className="nav-link" to="/proizvodi">Spavaća soba </NavLink>
                            </div>
                        </div>
                    </div>
                    <div className="col p-4">
                        <div style={{backgroundColor:"rgba(248, 249, 250,1)",textDecoration:"none"}}
                             className="card">
                            <div className="card-body">
                                <NavLink className="nav-link" to="/proizvodi">Kuhinja </NavLink>
                            </div>
                        </div>
                    </div>
                    <div className="col p-4">
                        <div style={{backgroundColor:"rgba(248, 249, 250,1)",textDecoration:"none"}}
                             className="card">
                            <div className="card-body">
                                <NavLink className="nav-link" to="/proizvodi">Dječija soba </NavLink>
                            </div>
                        </div>
                    </div>
                </div>
                <div className="row align-items-start py-4 fs-4">
                    <div className="col">
                        Izdvojeni proizvodi
                    </div>
                </div>
            </div>
        );
    }
}

export default CategoriesHome;