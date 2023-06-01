package ba.unsa.etf.nwt.order_service.controller;

import ba.unsa.etf.nwt.order_service.DTO.StateDTO;
import ba.unsa.etf.nwt.order_service.service.StateService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/state", produces = MediaType.APPLICATION_JSON_VALUE)
public class StateController {
    private final StateService stateService;

    public StateController(StateService stateService) {
        this.stateService = stateService;
    }

    @GetMapping("/state")
    public ResponseEntity<List<StateDTO>> all() {
        return new ResponseEntity<>(stateService.getAllStates(), HttpStatus.OK);
    }

    @PostMapping("/state")
    public ResponseEntity<Integer> newState(@RequestBody @Valid StateDTO newState) {
        return new ResponseEntity<>(stateService.addState(newState),HttpStatus.CREATED);
    }

    @GetMapping("/state/{id}")
    public ResponseEntity<StateDTO> one(@PathVariable Integer id) {
        return new ResponseEntity<>(stateService.getStateById(id),HttpStatus.OK);
    }

    @DeleteMapping("/state/{id}")
    public ResponseEntity<String> deleteState(@PathVariable Integer id) {
        stateService.deleteState(id);
        return new ResponseEntity<>("Successfully deleted!",HttpStatus.OK);
    }
}
