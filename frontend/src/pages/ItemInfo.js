import React, {Component} from "react";
import NavMenu from "../components/NavMenu";
import {Button, Col, Container, Image, Row, Table} from "react-bootstrap";
import UserService from "../services/UserService";
import AuthService from "../services/AuthService";

class ItemInfo extends Component{
    addToCart = () => {
        if(UserService.getUser() == null){
            window.location.href='/prijava'
        }
    }
    render() {
        return(
            <>
                <NavMenu/>
                <Container className={"m-4"}>
                    <Row className={"row"}>
                        <Col className={"col-8"}>
                            <Image src={"/images/living_room.jpg"} rounded />
                        </Col>
                        <Col className={"text-center"}>
                            <h1>Item title</h1>
                            <hr/>
                            <p className="lead text-center">Cijena</p>
                            <p className="lead text-center">Opis</p>
                            {(!AuthService.getCurrentUser() || AuthService.getCurrentUser().role==="customer") && <Button variant={"dark"} onClick={this.addToCart}>Korpa</Button>}
                        </Col>
                    </Row>
                    <Row className={"m-4"}>
                        <h3>Dodatne informacije</h3>
                        <hr/>
                        <Table fluid responsive="lg" className={"bg-body"}>
                            <tbody>
                            <tr>
                                <td>Dostupne boje</td>
                                <td>Table cell</td>
                            </tr>
                            <tr>
                                <td>Visina</td>
                                <td>Table cell</td>
                            </tr>
                            <tr>
                                <td>Širina</td>
                                <td>Table cell</td>
                            </tr>
                            <tr>
                                <td>Dužina</td>
                                <td>Table cell</td>
                            </tr>
                            <tr>
                                <td>Materijal</td>
                                <td>Table cell</td>
                            </tr>
                            </tbody>
                        </Table>
                    </Row>
                </Container>
            </>
        );
    }
}

export default ItemInfo;