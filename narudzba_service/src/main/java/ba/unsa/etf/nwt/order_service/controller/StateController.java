package ba.unsa.etf.nwt.order_service.controller;

import ba.unsa.etf.nwt.order_service.exception.NotFoundException;
import ba.unsa.etf.nwt.order_service.model.State;
import ba.unsa.etf.nwt.order_service.repository.StateRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StateController {
    private final StateRepository stateRepository;

    StateController(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    @GetMapping("/state")
    List<State> all() {
        return stateRepository.findAll();
    }

    @PostMapping("/state")
    State newState(@RequestBody State newState) {
        return stateRepository.save(newState);
    }

    @GetMapping("/state/{id}")
    State one(@PathVariable Integer id) {
        return stateRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id,"state"));
    }

    @PutMapping("/state/{id}")
    State replaceState(@RequestBody State newState, @PathVariable Integer id) {

        return stateRepository.findById(id)
                .map(state -> {
                    state.setState(newState.getState());
                    state.setOrders(newState.getOrders());
                    return stateRepository.save(state);
                }).orElseThrow(() -> new NotFoundException(id,"state"));
//                .orElseGet(() -> {
//                    newDate.setId(id);
//                    return repository.save(newEmployee);
//                });
    }

    @DeleteMapping("/state/{id}")
    void deleteState(@PathVariable Integer id) {
        stateRepository.deleteById(id);
    }
}
