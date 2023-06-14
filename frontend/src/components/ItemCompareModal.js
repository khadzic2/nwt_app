import React, {Component} from "react";
import {Button, Container, Image, Modal, Row, Table} from "react-bootstrap";

class ItemCompareModal extends Component{
    render() {
        return <>
            {
                <Modal show={this.props.open} onHide={this.props.onClose}>

                    <Modal.Header closeButton className={"bg-light"}>
                    </Modal.Header>
                    <Modal.Body className="bg-light show-grid overflow-x-scroll">
                        <Container>
                            <Row className={"flex-nowrap"}>
                                <Table className={"bg-light"}>
                                    <tbody>
                                        <tr>
                                            <td></td>
                                            <td className={"w-50"}>
                                                <Image src={"/images/living_room.jpg"}/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Naslov</td>
                                            <td>aaa</td>
                                        </tr>
                                        <tr>
                                            <td>Cijena</td>
                                            <td>aaa</td>
                                        </tr>
                                        <tr>
                                            <td>Opis</td>
                                            <td>aaa</td>
                                        </tr>
                                        <tr>
                                            <td>Boja</td>
                                            <td>aaa</td>
                                        </tr>
                                        <tr>
                                            <td>Dubina</td>
                                            <td>aaaa</td>
                                        </tr>
                                        <tr>
                                            <td>Visina</td>
                                            <td>aaa</td>
                                        </tr>
                                        <tr>
                                            <td>Širina</td>
                                            <td>aaa</td>
                                        </tr>
                                    </tbody>
                                </Table>
                            </Row>
                        </Container>
                    </Modal.Body>
                    <Modal.Footer className={"bg-light"}>
                        <Button variant={"dark"} className='bg-dark' onClick={this.props.onClose}>
                            Zatvori
                        </Button>
                    </Modal.Footer>

                </Modal>
            }

        </>
    }
}

export default ItemCompareModal;