import React, {Component} from "react";
import NavMenu from "../components/NavMenu";
import {Card, Col, Container, Row} from "react-bootstrap";
import OrderItemModal from "../components/OrderItemModal";

class Orders extends Component{
    constructor(props) {
        super(props);
        this.state={
            show_items:false
        }
    }

    handleShowItems=event=>{
        this.setState({show_items:true})
    }
    render() {
        return(
            <>
                <NavMenu/>
                <Container className="my-5">
                    <h3>Narudžbe</h3>
                    <hr />
                    <Row>
                        <Col className="text-center py-5">
                            <Card>
                                <Card.Header>
                                    <h4>Spisak aktivnih narudžbi</h4>
                                </Card.Header>
                                <Card.Body>
                                    <Row className="d-flex mb-4">
                                        <Col className="my-auto">
                                            <button onClick={this.handleShowItems} className="btn btn-outline-dark px-3">Prikaži proizvode</button>
                                        </Col>
                                        <Col className="my-auto">
                                            <p className="my-2">Stanje narudzbe</p>
                                            <p className="my-2">Datum isporuke</p>
                                            <p className="my-2">Datum odgode</p>
                                        </Col>
                                        <Col className="my-auto">
                                            <button className="btn btn-dark px-3">Obriši narudžbu</button>
                                        </Col>
                                    </Row>
                                    <hr/>
                                </Card.Body>
                            </Card>
                        </Col>
                    </Row>
                </Container>
                <OrderItemModal open={this.state.show_items} onClose={()=>this.setState({show_items:false})}/>
            </>
        );
    }

}

export default Orders;