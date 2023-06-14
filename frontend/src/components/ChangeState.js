import {Component} from "react";
import {Button, Form, Modal} from "react-bootstrap";

class ChangeState extends Component{
    render() {
        return (
            <>
                <Modal show={this.props.open} onHide={this.props.onClose}>
                    <Modal.Header closeButton className={"bg-light"}>
                        <Modal.Title>Izmjena stanja narudžbe</Modal.Title>
                    </Modal.Header>
                    <Modal.Body className={"bg-light"}>
                        <Form>
                            <Form.Group className="mb-3" controlId="exampleForm.ControlInput2">
                                <Form.Label>Stanje</Form.Label>
                                <Form.Select aria-label="Default select example">
                                    <option>Izaberite stanje</option>
                                    <option value="1">One</option>
                                    <option value="2">Two</option>
                                    <option value="3">Three</option>
                                </Form.Select>
                            </Form.Group>
                        </Form>
                    </Modal.Body>
                    <Modal.Footer className={"bg-light"}>
                        <Button variant="dark" onClick={this.props.onClose}>
                            Zatvori
                        </Button>
                        <Button variant="dark" onClick={this.props.onSave}>
                            Spasi promjene
                        </Button>
                    </Modal.Footer>
                </Modal>
            </>
        );
    }
}

export default ChangeState;