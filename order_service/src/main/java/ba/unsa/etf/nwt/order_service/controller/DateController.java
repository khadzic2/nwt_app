package ba.unsa.etf.nwt.order_service.controller;

import ba.unsa.etf.nwt.order_service.DTO.DateDTO;
import ba.unsa.etf.nwt.order_service.service.DateService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/date", produces = MediaType.APPLICATION_JSON_VALUE)
class DateController{

    private final DateService dateService;

    public DateController(DateService dateService) {
        this.dateService = dateService;
    }
    @PreAuthorize("hasAuthority('admin:read')")
    @GetMapping("/date")
    public ResponseEntity<List<DateDTO>> all() {
        return new ResponseEntity<>(dateService.getAllDates(), HttpStatus.OK);
    }
    @PreAuthorize("hasAnyAuthority('reg_user:read', 'admin:read')")
    @GetMapping("/date/{id}")
    public ResponseEntity<DateDTO> one(@PathVariable Integer id) {
        return new ResponseEntity<>(dateService.getDateById(id),HttpStatus.OK);
    }
    @PreAuthorize("hasAnyAuthority('reg_user:create', 'admin:create')")
    @PostMapping("/date")
    public ResponseEntity<Integer> newDate(@RequestBody @Valid DateDTO newDate) {
        return new ResponseEntity<>(dateService.addDate(newDate),HttpStatus.CREATED);
    }
    @PreAuthorize("hasAnyAuthority('reg_user:update', 'admin:update')")
    @PutMapping("/date/{id}")
    public ResponseEntity<String> replaceDate(@RequestBody @Valid DateDTO newDate, @PathVariable Integer id) {
        dateService.updateDate(newDate,id);
        return new ResponseEntity<>("Successfully updated!",HttpStatus.OK);
    }
    @PreAuthorize("hasAnyAuthority('reg_user:delete', 'admin:delete')")
    @DeleteMapping("/date/{id}")
    public ResponseEntity<String> deleteDate(@PathVariable Integer id) {
        dateService.deleteDate(id);
        return new ResponseEntity<>("Successfully deleted!",HttpStatus.OK);
    }
}
