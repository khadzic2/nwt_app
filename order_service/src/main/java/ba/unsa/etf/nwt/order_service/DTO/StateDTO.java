package ba.unsa.etf.nwt.order_service.DTO;


import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class StateDTO {

    private Integer id;
    @NotNull(message = "State type can't be null")
    private String state;

    public StateDTO() {
    }

    public StateDTO(String state) {
        this.state = state;
    }
}
