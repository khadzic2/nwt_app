import React, {Component} from "react";
import {Button, Card, ListGroup, ListGroupItem, Modal} from "react-bootstrap";

class ItemModal extends Component{

    render() {
        return <>
            {
                <Modal show={this.props.open} onHide={this.props.onClose}>

                    <Modal.Header closeButton className={"bg-light"}>
                        <Modal.Title>{this.props.itemtitle}</Modal.Title>
                    </Modal.Header>

                    <Modal.Body className={"bg-light"}>
                        <Card.Body>
                            <Card.Text>description</Card.Text>
                            <ListGroup>
                                <ListGroupItem>price</ListGroupItem>
                            </ListGroup>
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

export default ItemModal;