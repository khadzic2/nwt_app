package ba.unsa.etf.nwt.order_service.controller;

import ba.unsa.etf.nwt.order_service.model.State;
import ba.unsa.etf.nwt.order_service.service.StateService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StateController {
    private final StateService stateService;

    StateController(StateService stateService) {
        this.stateService = stateService;
    }

    @GetMapping("/state")
    ResponseEntity<List<State>> all() {
        return new ResponseEntity<>(stateService.getAllStates(), HttpStatus.OK);
    }

    @PostMapping("/state")
    ResponseEntity<State> newState(@RequestBody @Valid State newState) {
        return new ResponseEntity<>(stateService.addState(newState),HttpStatus.CREATED);
    }

    @GetMapping("/state/{id}")
    ResponseEntity<State> one(@PathVariable Integer id) {
        return new ResponseEntity<>(stateService.getStateById(id),HttpStatus.OK);
    }

    @DeleteMapping("/state/{id}")
    void deleteState(@PathVariable Integer id) {
        stateService.deleteState(id);
    }
}
