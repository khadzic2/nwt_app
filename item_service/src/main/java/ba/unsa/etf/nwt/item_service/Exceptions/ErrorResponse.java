package ba.unsa.etf.nwt.item_service.Exceptions;

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