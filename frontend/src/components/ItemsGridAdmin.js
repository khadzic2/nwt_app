import React, {Component} from "react";
import ItemModal from "./ItemModal";

class ItemsGridAdmin extends Component {
    constructor(props) {
        super(props);
        this.state = {
            items: ["item1", "item2", "item3", "item4", "item5", "item6", "item7", "item8", "item9"],
            item_title:"",
            show_modal: false
        }
    }

    handleModal = e => {
        this.setState({show_modal:true,item_title:e.currentTarget.id})
    }

    handleDeleteItem = e => {
        e.preventDefault();
    }
    render() {
        return(
            <>
                <div className="container my-3 py-3">
                    <div className="row">
                        { this.state.items.map(item => <div className="col-md-4 mb-4">
                            <div className="card text-center h-100">
                                <img
                                    className="card-img-top p-3"
                                    src="/images/living_room.jpg"
                                    alt="Card"
                                    onClick={() => {window.location.href='/proizvod'}}
                                />
                                <div className="card-body">
                                    <button id={item + ""} type="button" className="btn btn-dark m-1" onClick={this.handleModal}>
                                        Pregled
                                    </button>
                                    <button id={item + ""} type="button" className="btn btn-dark m-1" onClick={this.handleDeleteItem}>
                                        Obriši proizvod
                                    </button>
                                </div>
                            </div>
                        </div>)
                        }
                    </div>
                </div>
                <ItemModal itemtitle={this.state.item_title} open={this.state.show_modal} onClose={()=>this.setState({show_modal:false})}/>
            </>
        );
    }
}

export default ItemsGridAdmin;