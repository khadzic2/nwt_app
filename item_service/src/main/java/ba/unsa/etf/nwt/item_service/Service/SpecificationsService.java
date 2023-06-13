package ba.unsa.etf.nwt.item_service.Service;

import ba.unsa.etf.nwt.item_service.DTO.ItemCategoryDTO;
import ba.unsa.etf.nwt.item_service.DTO.ItemDTO;
import ba.unsa.etf.nwt.item_service.DTO.SpecificationsDTO;
import ba.unsa.etf.nwt.item_service.Exceptions.NotFoundException;
import ba.unsa.etf.nwt.item_service.Model.Item;
import ba.unsa.etf.nwt.item_service.Model.ItemCategory;
import ba.unsa.etf.nwt.item_service.Model.Specifications;
import ba.unsa.etf.nwt.item_service.Repository.SpecificationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpecificationsService {
    @Autowired
    private final SpecificationsRepository specificationsRepository;

    public SpecificationsService(SpecificationsRepository specificationsRepository) {
        this.specificationsRepository = specificationsRepository;
    }

    public List<SpecificationsDTO> getAllSpecifications(){
        return specificationsRepository.findAll().stream().map(date -> mapToDTO(date, new SpecificationsDTO())).collect(Collectors.toList());
    }

    public Specifications getSpecificaionsById(Integer id){
        return specificationsRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
    }

    public Integer addSpecifications(SpecificationsDTO specificationsDTO){
        Specifications specifications = new Specifications();
        mapToEntity(specificationsDTO,specifications);
        return specificationsRepository.save(specifications).getId();
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

    private void mapToEntity(final SpecificationsDTO specificationsDTO, final Specifications specifications) {
        specifications.setColors(specificationsDTO.getColors());
        specifications.setDepth(specificationsDTO.getDepth());
        specifications.setHeight(specificationsDTO.getHeight());
        specifications.setWidth(specificationsDTO.getWidth());
    }

    private SpecificationsDTO mapToDTO(final Specifications specifications, final SpecificationsDTO specificationsDTO) {
        specificationsDTO.setId(specifications.getId());
        specificationsDTO.setColors(specifications.getColors());
        specificationsDTO.setDepth(specifications.getDepth());
        specificationsDTO.setHeight(specifications.getHeight());
        specificationsDTO.setWidth(specifications.getWidth());
        return specificationsDTO;
    }
}