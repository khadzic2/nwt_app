package ba.unsa.etf.nwt.item_service.Response;

import lombok.Data;
@Data
public class CartExistsResponse {

    public CartExistsResponse() {
    }
    public CartExistsResponse(Integer itemId) {
        this.itemId = itemId;
    }
    private Integer itemId;//boolean add
}