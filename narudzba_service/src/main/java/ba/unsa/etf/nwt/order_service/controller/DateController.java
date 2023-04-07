package ba.unsa.etf.nwt.order_service.controller;

import java.util.List;

import ba.unsa.etf.nwt.order_service.exception.NotFoundException;
import ba.unsa.etf.nwt.order_service.model.Date;
import ba.unsa.etf.nwt.order_service.repository.DateRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class DateController{

    private final DateRepository dateRepository;

    DateController(DateRepository dateRepository) {
        this.dateRepository = dateRepository;
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/date")
    List<Date> all() {
        return dateRepository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/date")
    Date newEmployee(@RequestBody Date newDate) {
        return dateRepository.save(newDate);
    }

    // Single item

    @GetMapping("/date/{id}")
    Date one(@PathVariable Integer id) {
        return dateRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id,"date"));
    }

    @PutMapping("/date/{id}")
    Date replaceDate(@RequestBody Date newDate, @PathVariable Integer id) {

        return dateRepository.findById(id)
                .map(date -> {
                    date.setDelayDate(newDate.getDelayDate());
                    date.setDeliveryDate(newDate.getDeliveryDate());
                    date.setOrder(newDate.getOrder());
                    return dateRepository.save(date);
                }).orElseThrow(() -> new NotFoundException(id,"date"));
//                .orElseGet(() -> {
//                    newDate.setId(id);
//                    return repository.save(newEmployee);
//                });
    }

    @DeleteMapping("/date/{id}")
    void deleteDate(@PathVariable Integer id) {
        dateRepository.deleteById(id);
    }
}
