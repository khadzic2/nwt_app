package ba.unsa.etf.nwt.order_service.service;

import ba.unsa.etf.nwt.order_service.exception.NotAllowedRequest;
import ba.unsa.etf.nwt.order_service.exception.NotFoundException;
import ba.unsa.etf.nwt.order_service.model.Date;
import ba.unsa.etf.nwt.order_service.repository.DateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DateService {
    @Autowired
    private final DateRepository dateRepository;

    public DateService(DateRepository dateRepository) {
        this.dateRepository = dateRepository;
    }

    public List<Date> getAllDates(){
        return dateRepository.findAll();
    }

    public Date getDateById(Integer id){
        return dateRepository.findById(id).orElseThrow(()-> new NotFoundException(id,"date"));
    }

    public Date addDate(Date date){
        if (date.getDeliveryDate() != null && date.getDeliveryDate().isAfter(date.getDelayDate())) throw new NotAllowedRequest("Delay date must be after delivery date");
        return dateRepository.save(date);
    }

    public void deleteDate(Integer id){
        dateRepository.deleteById(id);
    }

    public void deleteAll(){
        dateRepository.deleteAll();
    }

    public Date updateDate(Date newDate, Integer id){
        Date oldDate = getDateById(id);

        if(newDate.getDelayDate() != null && oldDate.getDelayDate() != null && newDate.getDelayDate().isBefore(oldDate.getDelayDate())){
            throw new NotAllowedRequest("New delay date must be after old delay date");
        }

        oldDate.setDeliveryDate(newDate.getDeliveryDate());
        oldDate.setDelayDate(newDate.getDelayDate());
        dateRepository.save(oldDate);
        return oldDate;
    }
}
