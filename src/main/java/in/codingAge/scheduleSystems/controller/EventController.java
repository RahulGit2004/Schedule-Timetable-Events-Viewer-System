package in.codingAge.scheduleSystems.controller;

import in.codingAge.scheduleSystems.base.ApiResponse;
import in.codingAge.scheduleSystems.model.Event;
import in.codingAge.scheduleSystems.model.request.EventRequest;
import in.codingAge.scheduleSystems.model.request.UpdateEventReq;
import in.codingAge.scheduleSystems.service.EventService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
@CrossOrigin("*")
public class EventController {

    @Autowired
    private EventService eventService;


    @PostMapping("/create")
    public ApiResponse<Boolean> createEvent(@RequestBody EventRequest eventRequest) {
        return new ApiResponse<>(eventService.createEvent(eventRequest), HttpStatus.ACCEPTED);
    }

    @GetMapping("/all/upcoming")
    public ApiResponse<List<Event>> getAllUpcomingEvents(@RequestParam String studentId) {
        return new ApiResponse<>(eventService.getAllUpcomingEvents(studentId),HttpStatus.ACCEPTED);
    }

    @PutMapping("/update/detail")
    public ApiResponse<Boolean> updateEventDetails(@RequestBody UpdateEventReq eventReq) {
        return new ApiResponse<>(eventService.updateEventDetails(eventReq),HttpStatus.ACCEPTED);
    }

    @GetMapping("/all/upcoming/test")
    public ApiResponse<List<Event>> getAllUpcomingTest(@RequestParam String studentId) {
        return new ApiResponse<>(eventService.getAllUpcomingTest(studentId),HttpStatus.ACCEPTED);
    }

}
