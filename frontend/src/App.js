import './App.css';
import React, {Component} from 'react';
// import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
// import '../node_modules/font-awesome/css/font-awesome.min.css';
import {Route, BrowserRouter as Router, Routes} from "react-router-dom";
import Home from "./pages/Home";
import AboutUs from "./pages/AboutUs";
import Items from "./pages/Items";
import Login from "./pages/Login";
import Registration from "./pages/Registration";
class App extends Component{
    render() {
        return <Router>
            <Routes>
                <Route path='/' element={<Home/>} />
                <Route path='/o-nama' element={<AboutUs/>} />
                <Route path='/proizvodi' element={<Items/>} />
                <Route path='/prijava' element={<Login/>} />
                <Route path='/registracija' element={<Registration/>} />
            </Routes>
        </Router>
    }
}

export default App;
