package ba.unsa.etf.nwt.itemcart_service.service;

import ba.unsa.etf.nwt.itemcart_service.exception.NotFoundException;
import ba.unsa.etf.nwt.itemcart_service.model.SelectedSpecifications;
import ba.unsa.etf.nwt.itemcart_service.repository.SelectedSpecificationsRepository;
import org.springframework.stereotype.Service;

@Service
public class SelectedSpecificationsService {
    private final SelectedSpecificationsRepository selectedSpecificationsRepository;

    public SelectedSpecificationsService(SelectedSpecificationsRepository selectedSpecificationsRepository) {
        this.selectedSpecificationsRepository = selectedSpecificationsRepository;
    }

    public SelectedSpecifications getSpecificationsById(Integer id){
        return selectedSpecificationsRepository.findById(id).orElseThrow(()-> new NotFoundException(id,"selected specifications"));
    }

    public void deleteAll(){
        selectedSpecificationsRepository.deleteAll();
    }

    public SelectedSpecifications add(SelectedSpecifications selectedSpecifications) {
        return selectedSpecificationsRepository.save(selectedSpecifications);
    }
}
