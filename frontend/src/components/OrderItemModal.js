import React, {Component} from "react";
import {Button, Card, Col, Image, Modal, Row} from "react-bootstrap";

class OrderItemModal extends Component{
    render() {
        return <>
            {
                <Modal show={this.props.open} onHide={this.props.onClose}>

                    <Modal.Header closeButton className={"bg-light"}>
                        <Modal.Title>Proizvodi</Modal.Title>
                    </Modal.Header>

                    <Modal.Body className={"bg-light"}>
                        <Card.Body className="overflow-">
                            <Row>
                                <Col><Image src={"/images/living_room.jpg"}/></Col>
                                <Col className="my-auto">
                                    <h5 className="my-2">Naziv proizvoda</h5>
                                    <p className="my-2">Cijena</p>
                                    <p className="my-2">Boja</p>
                                    <p className="my-2">Visina x sirina x dubina</p>
                                    <p className="my-2">Materijal</p>
                                    <p className="my-2">Kolicina</p>
                                </Col>
                            </Row>
                        </Card.Body>
                    </Modal.Body>

                    <Modal.Footer className={"bg-light"}>
                        <Button className='bg-dark' onClick={this.props.onClose}>
                            Zatvori
                        </Button>
                    </Modal.Footer>

                </Modal>
            }

        </>
    }
}

export default OrderItemModal;