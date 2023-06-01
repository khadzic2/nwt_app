package ba.unsa.etf.nwt.system_events_service.controllers;

import ba.unsa.etf.nwt.system_events_service.models.SystemEvent;
import ba.unsa.etf.nwt.system_events_service.repositories.SystemEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/api/comment")
public class SystemEventController {
    @Autowired
    private SystemEventRepository systemEventRepository;


    @GetMapping(path = "/system-events")
    public  @ResponseBody
    Iterable<SystemEvent> getAllSystemEvents(){
        return systemEventRepository.findAll();
    }
}
