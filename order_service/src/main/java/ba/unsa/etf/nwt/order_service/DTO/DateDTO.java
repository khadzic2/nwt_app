package ba.unsa.etf.nwt.order_service.DTO;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Getter
@Setter
public class DateDTO {
    private Integer id;
    @NotNull(message = "Delivery date can't be null")
    @FutureOrPresent(message = "Delivery date can't be in past")
    private LocalDate deliveryDate;
    @FutureOrPresent(message = "Delay date can't be in past")
    private LocalDate delayDate;

    public DateDTO() {
    }

    public DateDTO(LocalDate deliveryDate, LocalDate delayDate) {
        this.deliveryDate = deliveryDate;
        this.delayDate = delayDate;
    }
}
