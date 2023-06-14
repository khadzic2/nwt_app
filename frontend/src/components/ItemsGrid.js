import React, {Component} from "react";
import ItemModal from "./ItemModal";
import ItemCompareModal from "./ItemCompareModal";
import AddToCart from "./AddToCart";
import AuthService from "../services/AuthService";

class ItemsGrid extends Component{
    constructor(props) {
        super(props);
        this.state = {
            items: ["item1", "item2", "item3", "item4", "item5", "item6", "item7", "item8", "item9"],
            item_title:"",
            show_modal: false,
            compare_modal:false,
            add_to_cart:false,
            compare_items:[]
        }
    }

    addToCart = () => {
        if(!AuthService.getCurrentUser()){
            window.location.href='/prijava';
        }else{
            this.setState({add_to_cart:true})
        }
    }

    compareItems = e => {
        let items = [...this.state.compare_items,e.currentTarget.id];
        this.setState({compare_modal:true,compare_items:items})
    }

    handleModal = e => {
        this.setState({show_modal:true,item_title:e.currentTarget.id})
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
                                        <button className="btn btn-dark m-1" onClick={this.addToCart}>
                                            Korpa
                                        </button>
                                        <button id={item + ""} type="button" className="btn btn-dark m-1" onClick={this.compareItems}>
                                            Uporedi
                                        </button>
                                    </div>
                                </div>
                            </div>)
                         }
                    </div>
                </div>
                <ItemModal itemtitle={this.state.item_title} open={this.state.show_modal} onClose={()=>this.setState({show_modal:false})}/>
                <ItemCompareModal items={this.state.compare_items} open={this.state.compare_modal} onClose={()=>this.setState({compare_modal:false})} />
                <AddToCart open={this.state.add_to_cart} onClose={()=>this.setState({add_to_cart:false})} onSave={()=>window.location.href='/korpa'}/>
            </>
        );
    }
}

export default ItemsGrid;