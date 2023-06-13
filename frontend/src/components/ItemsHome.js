import {Component} from "react";
import Carousel from "react-multi-carousel";
import "react-multi-carousel/lib/styles.css";
import "../style/MultiCarouselHome.css"
class ItemsHome extends Component{
    constructor(props) {
        super(props)
        this.state = {
            items: ["item1","item2","item3","item4","item5","item6","item7","item8","item9"]
        };
    }
    render() {
        const responsive = {
            desktop: {
                breakpoint: { max: 3000, min: 1024 },
                items: 4
            }
        };

        return(
            <Carousel responsive={responsive} className="m-4">
                {this.state.items.map(item=>(
                    <div className="card mx-3 p-3">
                        <img src="/images/living_room.jpg" className="card-img-top" alt="..."/>
                        <div className="card-body">
                            <p className="card-text">{item}</p>
                        </div>
                    </div>
                ))}
            </Carousel>
        );
    }
}

export default ItemsHome;