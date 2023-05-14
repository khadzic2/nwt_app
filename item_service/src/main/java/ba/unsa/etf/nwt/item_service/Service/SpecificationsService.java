package ba.unsa.etf.nwt.item_service.Service;

import ba.unsa.etf.nwt.item_service.Exceptions.NotFoundException;
import ba.unsa.etf.nwt.item_service.Model.Specifications;
import ba.unsa.etf.nwt.item_service.Repository.SpecificationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecificationsService {
    @Autowired
    private final SpecificationsRepository specificationsRepository;

    public SpecificationsService(SpecificationsRepository specificationsRepository) {
        this.specificationsRepository = specificationsRepository;
    }

    public List<Specifications> getAllSpecifications(){
        return specificationsRepository.findAll();
    }

    public Specifications getSpecificaionsById(Integer id){
        return specificationsRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
    }

    public Specifications addSpecifications(Specifications specifications){
        return specificationsRepository.save(specifications);
    }

    public void deleteSpecifications(Integer id){
        specificationsRepository.deleteById(id);
    }

    public void deleteAll(){
        specificationsRepository.deleteAll();
    }

   /* public Specifications updateSpecifications(Specifications newSpecification, Integer id){
        Specifications oldSpecifications = getSpecificaionsById(id);

        oldSpecifications.setSpecifications(newSpecification.getSpecifications());

        specificationsRepository.save(oldSpecifications);
        return oldSpecifications;
    }*/
}