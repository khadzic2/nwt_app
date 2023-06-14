import React, {Component} from "react";
import NavMenu from "../components/NavMenu";
import ItemsGrid from "../components/ItemsGrid";

class Items extends Component{
    constructor(props) {
        super(props);
        this.state={
            category:""
        };
    }
    handleClick = event =>{
        this.setState({category:event.currentTarget.id})
    }
    render() {
        return(
            <>
                <NavMenu/>
                <div className="container text-center">
                    <div className="row align-items-start">
                        <div className="col-10">
                            <ItemsGrid/>
                        </div>
                        <div className="col">
                            <div className="container my-3 py-3">
                                <div className="row align-items-start py-4 fs-5">
                                    <div className="col">
                                        Kategorije
                                    </div>
                                </div>
                                <ul className="list-group">
                                    <li className="list-group-item list-group-item-secondary">Dnevna soba</li>
                                    <button type="button" className="list-group-item list-group-item-action" id={"Kauč"} onClick={this.handleClick}>
                                        Kauč
                                    </button>
                                    <button type="button" className="list-group-item list-group-item-action" id={"Sto"} onClick={this.handleClick}>
                                        Sto
                                    </button>
                                    <button type="button" className="list-group-item list-group-item-action" id={"Stolica"} onClick={this.handleClick}>
                                        Stolica
                                    </button>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </>
        );
    }
}

export default Items;