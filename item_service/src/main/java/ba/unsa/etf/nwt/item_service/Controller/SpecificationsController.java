package ba.unsa.etf.nwt.item_service.Controller;


import java.util.List;

import ba.unsa.etf.nwt.item_service.Exceptions.NotFoundException;
import ba.unsa.etf.nwt.item_service.Model.Item;
import ba.unsa.etf.nwt.item_service.Model.Specifications;
import ba.unsa.etf.nwt.item_service.Repository.SpecificationsRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/specifications", produces = MediaType.APPLICATION_JSON_VALUE)
public class SpecificationsController {

    private final SpecificationsRepository repository;

    SpecificationsController(SpecificationsRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/specifications")
    List<Specifications> all() {
        return repository.findAll();
    }


    @PostMapping("/specifications")
    Specifications newSpecification(@RequestBody Specifications specifications) {
        return repository.save(specifications);
    }

}
