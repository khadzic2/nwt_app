import React, {Component} from "react";
import NavMenu from "../components/NavMenu";
import {Button, Card, Col, Container, Image, Row} from "react-bootstrap";
import OrderModal from "../components/OrderModal";

class Cart extends Component{
    constructor(props) {
        super(props);
        this.state={
            orderModal:false
        }
    }

    handleOrder = event =>{
        this.setState({orderModal:true})
    }
    render() {
        return (
            <>
                <NavMenu/>
                <Container className="my-5">
                    <h3>Korpa</h3>
                    <hr />
                    <Row>
                        <Col className="text-center py-5" xs={12} lg={8}>
                            <Card>
                                <Card.Header>
                                    <h4>Proizvodi sa izabranim specifikacijama</h4>
                                </Card.Header>
                                <Card.Body>
                                    <Row className="d-flex mb-4">
                                        <Col><Image src={"/images/living_room.jpg"}/></Col>
                                        <Col className="my-auto">
                                            <h5>Naziv proizvoda</h5>
                                            <p>Cijena</p>
                                            <p>Boja</p>
                                            <p>Visina x sirina x dubina</p>
                                            <p>Materijal</p>
                                        </Col>
                                        <Col className="my-auto">
                                            <button className="btn btn-dark px-3 my-2">
                                                -
                                            </button>
                                            <label className="mx-2">kolicina</label>
                                            <button className="btn btn-dark px-3 my-2">
                                                +
                                            </button>
                                        </Col>
                                        <Col className="my-auto">
                                            <button className="btn btn-dark px-3">Obrisi proizvod</button>
                                        </Col>
                                    </Row>
                                    <hr/>
                                </Card.Body>
                            </Card>
                        </Col>
                        <Col className="text-center py-5" xs={12} lg={4}>
                            <Card>
                                <Card.Header>
                                    <h4>Ukupna cijena narudžbe</h4>
                                </Card.Header>
                                <Card.Body>
                                    <Card.Text>
                                        Ukupna cijena
                                    </Card.Text>
                                    <Button onClick={this.handleOrder} variant="dark">Naruči</Button>
                                </Card.Body>
                            </Card>
                        </Col>
                    </Row>
                </Container>
                <OrderModal open={this.state.orderModal} onChange={event=>console.log(event.target.value)} onClose={()=>this.setState({orderModal:false})} onSave={()=>this.setState({orderModal:false})}/>
            </>
        );
    }
}

export default Cart;