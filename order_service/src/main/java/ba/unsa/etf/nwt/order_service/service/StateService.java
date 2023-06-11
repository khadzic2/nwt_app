package ba.unsa.etf.nwt.order_service.service;

import ba.unsa.etf.nwt.order_service.DTO.StateDTO;
import ba.unsa.etf.nwt.order_service.exception.NotFoundException;
import ba.unsa.etf.nwt.order_service.model.State;
import ba.unsa.etf.nwt.order_service.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StateService {
    @Autowired
    private final StateRepository stateRepository;

    public StateService(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    public List<StateDTO> getAllStates(){
        return stateRepository.findAll().stream().map(state -> mapToDTO(state, new StateDTO())).collect(Collectors.toList());
    }

    public StateDTO getStateById(Integer id){
        return stateRepository.findById(id).map(state -> mapToDTO(state, new StateDTO())).orElseThrow(()-> new NotFoundException(id,"state"));
    }

    public Integer addState(StateDTO stateDTO){
        State state = new State();
        mapToEntity(stateDTO,state);
        return stateRepository.save(state).getId();
    }

    public void deleteState(Integer id){
        stateRepository.deleteById(id);
    }

    public void deleteAll(){
        stateRepository.deleteAll();
    }

    private StateDTO mapToDTO(final State state, final StateDTO stateDTO) {
        stateDTO.setId(state.getId());
        stateDTO.setState(state.getState());
        return stateDTO;
    }

    private void mapToEntity(final StateDTO stateDTO, final State state) {
        state.setState(stateDTO.getState());
    }
}
