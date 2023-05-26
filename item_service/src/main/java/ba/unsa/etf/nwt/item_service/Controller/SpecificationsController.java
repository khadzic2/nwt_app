package ba.unsa.etf.nwt.item_service.Controller;


import java.util.List;

import ba.unsa.etf.nwt.item_service.Exceptions.NotFoundException;
import ba.unsa.etf.nwt.item_service.Model.Item;
import ba.unsa.etf.nwt.item_service.Model.Specifications;
import ba.unsa.etf.nwt.item_service.Repository.SpecificationsRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController

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
