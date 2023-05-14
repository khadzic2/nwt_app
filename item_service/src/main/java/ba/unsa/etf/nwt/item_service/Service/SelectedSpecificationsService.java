package ba.unsa.etf.nwt.item_service.Service;

import ba.unsa.etf.nwt.item_service.Exceptions.NotFoundException;
import ba.unsa.etf.nwt.item_service.Model.SelectedSpecifications;
import ba.unsa.etf.nwt.item_service.Repository.SelectedSpecificationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelectedSpecificationsService {
    @Autowired
    private final SelectedSpecificationsRepository selectedSpecificationsRepository;

    public SelectedSpecificationsService(SelectedSpecificationsRepository selectedSpecificationsRepository) {
        this.selectedSpecificationsRepository = selectedSpecificationsRepository;
    }

    public List<SelectedSpecifications> getAllSelectedSpecifications(){
        return selectedSpecificationsRepository.findAll();
    }

    public SelectedSpecifications getSelectedSpecificationById(Integer id){
        return selectedSpecificationsRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
    }

    public SelectedSpecifications addSelected(SelectedSpecifications selected){
        return selectedSpecificationsRepository.save(selected);
    }

    public void deleteSelected(Integer id){
        selectedSpecificationsRepository.deleteById(id);
    }

    public void deleteAll(){
        selectedSpecificationsRepository.deleteAll();
    }

}