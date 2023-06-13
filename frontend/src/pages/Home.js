import React, {Component} from "react";
import NavMenu from "../components/NavMenu";
import "../style/Home.css"

class Home extends Component {
    render() {
        return (
            <>
                <NavMenu/>
                <div id="carouselExample" className="carousel m-4">
                    <div className="carousel-inner">
                        <div className="carousel-item active">
                            <img src="/images/furniture_carousel_1.jpg" className="d-block" alt="picture1"/>
                        </div>
                        <div className="carousel-item">
                            <img src="/images/furniture_carousel_2.jpg" className="d-block" alt="picture2"/>
                        </div>
                        <div className="carousel-item">
                            <img src="/images/furniture_carousel_3.jpg" className="d-block" alt="picture3"/>
                        </div>
                    </div>
                    <button className="carousel-control-prev" type="button" data-bs-target="#carouselExample"
                            data-bs-slide="prev">
                        <span className="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span className="visually-hidden">Previous</span>
                    </button>
                    <button className="carousel-control-next" type="button" data-bs-target="#carouselExample"
                            data-bs-slide="next">
                        <span className="carousel-control-next-icon" aria-hidden="true"></span>
                        <span className="visually-hidden">Next</span>
                    </button>
                </div>
                <div className="text-center m-4">
                    <div className="row align-items-start py-4 fs-3">
                        <div className="col">
                            <i>Opremite svoju sobu najkvalitetnijim namještajem</i>
                            <hr/>
                            <i>Registrujte se i ostvarite svoje ideje</i>
                        </div>
                    </div>
                </div>
            </>
        );
    }
}

export default Home;