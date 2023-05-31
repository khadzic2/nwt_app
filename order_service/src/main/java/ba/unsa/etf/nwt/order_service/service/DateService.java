package ba.unsa.etf.nwt.order_service.service;

import ba.unsa.etf.nwt.order_service.DTO.DateDTO;
import ba.unsa.etf.nwt.order_service.exception.NotAllowedRequest;
import ba.unsa.etf.nwt.order_service.exception.NotFoundException;
import ba.unsa.etf.nwt.order_service.model.Date;
import ba.unsa.etf.nwt.order_service.repository.DateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DateService {
    @Autowired
    private final DateRepository dateRepository;

    public DateService(DateRepository dateRepository) {
        this.dateRepository = dateRepository;
    }

    public List<DateDTO> getAllDates(){
        return dateRepository.findAll().stream().map(date -> mapToDTO(date, new DateDTO())).collect(Collectors.toList());
    }

    public DateDTO getDateById(Integer id){
        return dateRepository.findById(id).map(date-> mapToDTO(date,new DateDTO())).orElseThrow(()-> new NotFoundException(id,"date"));
    }

    public Integer addDate(DateDTO dateDTO){
        Date date = new Date();
        mapToEntity(dateDTO,date);
        if (dateDTO.getDeliveryDate() != null && dateDTO.getDeliveryDate().isAfter(dateDTO.getDelayDate())) throw new NotAllowedRequest("Delay date must be after delivery date");
        return dateRepository.save(date).getId();
    }

    public void deleteDate(Integer id){
        dateRepository.deleteById(id);
    }

    public void deleteAll(){
        dateRepository.deleteAll();
    }

    public void updateDate(DateDTO newDate, Integer id){
        Date oldDate = dateRepository.findById(id).orElseThrow(()-> new NotFoundException(id,"date"));

        if(newDate.getDelayDate() != null && oldDate.getDelayDate() != null && newDate.getDelayDate().isBefore(oldDate.getDelayDate())){
            throw new NotAllowedRequest("New delay date must be after old delay date");
        }

        mapToEntity(newDate,oldDate);
        dateRepository.save(oldDate);
    }

    private DateDTO mapToDTO(final Date date, final DateDTO dateDTO) {
       dateDTO.setId(date.getId());
       dateDTO.setDelayDate(date.getDelayDate());
       dateDTO.setDeliveryDate(date.getDeliveryDate());
       return dateDTO;
    }

    private void mapToEntity(final DateDTO dateDTO, final Date date) {
        date.setDelayDate(dateDTO.getDelayDate());
        date.setDeliveryDate(dateDTO.getDeliveryDate());
    }
}
