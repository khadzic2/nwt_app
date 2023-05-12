package ba.unsa.etf.nwt.order_service.controller;

import ba.unsa.etf.nwt.order_service.model.Date;
import ba.unsa.etf.nwt.order_service.service.DateService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
class DateController{

    private final DateService dateService;

    DateController(DateService dateService) {
        this.dateService = dateService;
    }

    @GetMapping("/date")
    ResponseEntity<List<Date>> all() {
        return new ResponseEntity<>(dateService.getAllDates(), HttpStatus.OK);
    }

    @PostMapping("/date")
    ResponseEntity<Date> newDate(@RequestBody @Valid Date newDate) {
        return new ResponseEntity<>(dateService.addDate(newDate),HttpStatus.CREATED);
    }

    @GetMapping("/date/{id}")
    ResponseEntity<Date> one(@PathVariable Integer id) {
        return new ResponseEntity<>(dateService.getDateById(id),HttpStatus.OK);
    }

    @PutMapping("/date/{id}")
    ResponseEntity<Date> replaceDate(@RequestBody @Valid Date newDate, @PathVariable Integer id) {
        return new ResponseEntity<>(dateService.updateDate(newDate,id),HttpStatus.OK);
    }

    @DeleteMapping("/date/{id}")
    void deleteDate(@PathVariable Integer id) {
        dateService.deleteDate(id);
    }
}
