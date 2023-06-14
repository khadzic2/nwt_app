import React, {Component} from "react";
import {Button, Modal} from "react-bootstrap";
import Form from 'react-bootstrap/Form';
class OrderModal extends Component{

    render() {
        return <>
            {
                <Modal show={this.props.open} onHide={this.props.onClose}>

                    <Modal.Header closeButton className={"bg-light"}>
                        <Modal.Title>Odaberite datum odgode</Modal.Title>
                    </Modal.Header>

                    <Modal.Body className={"bg-light"}>
                        <div className="row">
                            <div className="col-md-4">
                                <Form.Label>Datum isporuke</Form.Label>
                                <Form.Control disabled type="date" name="dob" defaultValue={"2023-06-20"} defaultChecked={true}/>
                            </div>
                            <div className="col-md-4">
                                <Form.Label>Datum odgode</Form.Label>
                                <Form.Control type="date" name="dob" onChange={this.props.onChange} />
                            </div>
                        </div>
                    </Modal.Body>

                    <Modal.Footer className={"bg-light"}>
                        <Button className='bg-dark' onClick={this.props.onClose}>
                            Zatvori
                        </Button>
                        <Button className='bg-dark' onClick={this.props.onSave}>
                            Naruči
                        </Button>
                    </Modal.Footer>

                </Modal>
            }

        </>
    }
}

export default OrderModal;