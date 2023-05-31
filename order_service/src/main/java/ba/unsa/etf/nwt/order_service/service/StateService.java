package ba.unsa.etf.nwt.order_service.service;

import ba.unsa.etf.nwt.order_service.exception.NotFoundException;
import ba.unsa.etf.nwt.order_service.model.State;
import ba.unsa.etf.nwt.order_service.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateService {
    @Autowired
    private final StateRepository stateRepository;

    public StateService(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    public List<State> getAllStates(){
        return stateRepository.findAll();
    }

    public State getStateById(Integer id){
        return stateRepository.findById(id).orElseThrow(()-> new NotFoundException(id,"state"));
    }

    public State addState(State state){
        return stateRepository.save(state);
    }

    public void deleteState(Integer id){
        stateRepository.deleteById(id);
    }

    public void deleteAll(){
        stateRepository.deleteAll();
    }
}
