import './App.css';
import React, {Component} from 'react';
import {Route, BrowserRouter as Router, Routes} from "react-router-dom";
import Home from "./pages/Home";
import AboutUs from "./pages/AboutUs";
import Items from "./pages/Items";
import Login from "./pages/Login";
import Registration from "./pages/Registration";
import ItemInfo from "./pages/ItemInfo";
import Cart from "./pages/Cart";
import Orders from "./pages/Orders";
import ItemsAdmin from "./pages/ItemsAdmin";
import OrdersAdmin from "./pages/OrdersAdmin";
class App extends Component{
    render() {
        return <Router>
            <Routes>
                <Route path='/' element={<Home/>} />
                <Route path='/pocetna' element={<Home/>} />
                <Route path='/o-nama' element={<AboutUs/>} />
                <Route path='/proizvodi' element={<Items/>} />
                <Route path='/prijava' element={<Login/>} />
                <Route path='/registracija' element={<Registration/>} />
                <Route path='/proizvod' element={<ItemInfo/>}/>
                <Route path='/korpa' element={<Cart/>}/>
                <Route path='/narudzbe' element={<Orders/>}/>
                <Route path='/proizvodi-admin' element={<ItemsAdmin/>}/>
                <Route path='/narudzbe-admin' element={<OrdersAdmin/>}/>
            </Routes>
        </Router>
    }
}

export default App;
