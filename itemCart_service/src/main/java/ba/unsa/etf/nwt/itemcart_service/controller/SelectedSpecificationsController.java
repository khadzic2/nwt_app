package ba.unsa.etf.nwt.itemcart_service.controller;

import ba.unsa.etf.nwt.itemcart_service.model.Cart;
import ba.unsa.etf.nwt.itemcart_service.model.SelectedSpecifications;
import ba.unsa.etf.nwt.itemcart_service.service.CartService;
import ba.unsa.etf.nwt.itemcart_service.service.SelectedSpecificationsService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RequestMapping(path = "/api")
@RestController
public class SelectedSpecificationsController {
    private final SelectedSpecificationsService selectedSpecificationsService;

    public SelectedSpecificationsController(SelectedSpecificationsService selectedSpecificationsService) {
        this.selectedSpecificationsService = selectedSpecificationsService;
    }

    @PostMapping("/specifications")
    ResponseEntity<SelectedSpecifications> newSpecifications(@Valid @RequestBody SelectedSpecifications selectedSpecifications) {
        return new ResponseEntity<>(selectedSpecificationsService.add(selectedSpecifications),HttpStatus.CREATED);
    }
    @GetMapping("/specifications/{id}")
    ResponseEntity<SelectedSpecifications> one(@PathVariable Integer id) {
        return new ResponseEntity<>(selectedSpecificationsService.getSpecificationsById(id),HttpStatus.OK);
    }
}
