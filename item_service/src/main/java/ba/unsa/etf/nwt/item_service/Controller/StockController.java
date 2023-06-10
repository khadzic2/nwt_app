package ba.unsa.etf.nwt.item_service.Controller;

import ba.unsa.etf.nwt.item_service.Model.Stock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/stock", produces = MediaType.APPLICATION_JSON_VALUE)
public class StockController {

    @GetMapping("/stock/{itemId}")
    public ResponseEntity<Integer> getStockQuantity(@PathVariable String itemId) {
        // Retrieve the stock quantity for the given item ID
        Stock stock = null;
        int stockQuantity = stock.getAmount();

        return ResponseEntity.status(HttpStatus.OK).body(stockQuantity);
    }
}
