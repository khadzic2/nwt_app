package ba.unsa.etf.nwt.item_service.Controller;


import java.util.List;

import ba.unsa.etf.nwt.item_service.DTO.SpecificationsDTO;
import ba.unsa.etf.nwt.item_service.Service.SpecificationsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/specifications", produces = MediaType.APPLICATION_JSON_VALUE)
public class SpecificationsController {
    private final SpecificationsService specificationsService;


    SpecificationsController(SpecificationsService specificationsService) {
        this.specificationsService = specificationsService;
    }

    @GetMapping("/specifications")
    public ResponseEntity<List<SpecificationsDTO>> all() {
        return new ResponseEntity<>(specificationsService.getAllSpecifications(), HttpStatus.OK);
    }

    @PostMapping("/specifications")
    public ResponseEntity<Integer> newSpecifications(@RequestBody @Valid SpecificationsDTO newSpecifications) {
        return new ResponseEntity<>(specificationsService.addSpecifications(newSpecifications),HttpStatus.CREATED);
    }

}
