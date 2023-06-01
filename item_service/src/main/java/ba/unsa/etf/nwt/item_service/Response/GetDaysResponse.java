package ba.unsa.etf.nwt.item_service.Response;

import lombok.Data;

@Data
public class GetDaysResponse {

    public GetDaysResponse(Integer itemId, String color, String height, String width, String depth, String material) {
        this.itemId = itemId;
        this.color = color;
        this.height = height;
        this.width = width;
        this.depth = depth;
        this.material = material;
    }
    private Integer itemId;
    private String color;

    private String height;

    private String width;

    private String depth;

    private String material;
}
