package ba.unsa.etf.nwt.itemcart_service.exception;



import lombok.Data;

import java.util.List;

@Data
public class ErrorResponse {
    private String error;
    private List<String> message;

    public ErrorResponse(){}

    public ErrorResponse(String error, List<String> message) {
        this.error = error;
        this.message = message;
    }
}
