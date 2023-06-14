import React, {Component} from "react";
import NavMenu from "../components/NavMenu";
import ItemsGridAdmin from "../components/ItemsGridAdmin";
import AddItem from "../components/AddItem";

class ItemsAdmin extends Component{
    constructor(props) {
        super(props);
        this.state={
            category:"",
            add_item:false
        };
    }
    handleCategory = event =>{
        this.setState({category:event.currentTarget.id})
    }
    handleAddItem = event =>{
        this.setState({add_item:true})
    }
    render() {
        return(
            <>
                <NavMenu/>
                <div className="container text-center">
                    <div className="row align-items-start">
                        <div className="col-10">
                            <ItemsGridAdmin/>
                        </div>
                        <div className="col">
                            <div className="container my-3 py-3">
                                <button onClick={this.handleAddItem} className="btn btn-outline-dark px-3">Dodaj proizvod</button>
                                <div className="row align-items-start py-4 fs-5">
                                    <div className="col">
                                        Kategorije
                                    </div>
                                </div>
                                <ul className="list-group">
                                    <li className="list-group-item list-group-item-secondary">Dnevna soba</li>
                                    <button type="button" className="list-group-item list-group-item-action" id={"Kauč"} onClick={this.handleCategory}>
                                        Kauč
                                    </button>
                                    <button type="button" className="list-group-item list-group-item-action" id={"Sto"} onClick={this.handleCategory}>
                                        Sto
                                    </button>
                                    <button type="button" className="list-group-item list-group-item-action" id={"Stolica"} onClick={this.handleCategory}>
                                        Stolica
                                    </button>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <AddItem open={this.state.add_item} onClose={()=>this.setState({add_item:false})} onSave={()=>this.setState({add_item:false})}/>
            </>
        );
    }
}

export default ItemsAdmin;