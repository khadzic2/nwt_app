import React, {Component} from "react";
import NavMenu from "../components/NavMenu";

class AboutUs extends Component{
    render() {
        return(
            <>
                <NavMenu/>
                <div className="container my-3 py-3">
                    <h1 className="text-center">O nama</h1>
                    <hr />
                    <p className="lead text-center">
                        Lorem ipsum dolor sit amet consectetur adipisicing elit. Nostrum
                        facere doloremque veritatis odit similique sequi. Odit amet fuga nam
                        quam quasi facilis sed doloremque saepe sint perspiciatis explicabo
                        totam vero quas provident ipsam, veritatis nostrum velit quos
                        recusandae est mollitia esse fugit dolore laudantium. Ex vel explicabo
                        earum unde eligendi autem praesentium, doloremque distinctio nesciunt
                        porro tempore quis eaque labore voluptatibus ea necessitatibus
                        exercitationem tempora molestias. Ad consequuntur veniam sequi ullam
                        tempore vel tenetur soluta dolore sunt maxime aliquam corporis est,
                        quo saepe dolorem optio minus sint nemo totam dolorum! Reprehenderit
                        delectus expedita a alias nam recusandae illo debitis repellat libero,
                        quasi explicabo molestiae saepe, dolorem tempore itaque eveniet quam
                        dignissimos blanditiis excepturi harum numquam vel nihil? Ipsum
                    </p>
                    <hr/>
                    <p>
                        Za sve informacije smo dostupni putem e-maila: home_harmony@gmail.com,
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