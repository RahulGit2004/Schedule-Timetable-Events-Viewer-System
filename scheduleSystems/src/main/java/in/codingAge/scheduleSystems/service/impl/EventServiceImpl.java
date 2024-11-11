package in.codingAge.scheduleSystems.service.impl;

import in.codingAge.scheduleSystems.exception.AppException;
import in.codingAge.scheduleSystems.model.Batch;
import in.codingAge.scheduleSystems.model.Event;
import in.codingAge.scheduleSystems.model.User;
import in.codingAge.scheduleSystems.model.request.EventRequest;
import in.codingAge.scheduleSystems.model.request.UpdateEventReq;
import in.codingAge.scheduleSystems.repository.EventRepository;
import in.codingAge.scheduleSystems.service.BatchService;
import in.codingAge.scheduleSystems.service.EventService;
import in.codingAge.scheduleSystems.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private BatchService batchService;

    @Autowired
    private UserService userService;

    @Override
    public boolean createEvent(EventRequest eventRequest) {
        Batch batch = batchService.getBatchByBatchId(eventRequest.getBatchId());
        if (batch == null) {
            throw new AppException("Invalid Batch");
        } else {
            User admin = userService.getUserByUserId(eventRequest.getCreatorId());
            if (admin == null || admin.getUserRole().equalsIgnoreCase("student")) {
                throw new AppException("You are not authorize");
            } else {
                Event event = getEvent(eventRequest);
                eventRepository.save(event);
                return true;
            }
        }
    }

    private static Event getEvent(EventRequest eventRequest) {
        Event event = new Event();
        event.setBatchId(event.getBatchId());
        event.setEventType(event.getEventType());
        event.setCreatorId(event.getCreatorId());
        event.setDate(eventRequest.getDate());
        event.setTime(eventRequest.getTime());
        event.setDescription(event.getDescription());
        event.setLocation(event.getLocation());
        event.setTitle(event.getTitle());
        event.setOrganizer(event.getOrganizer());
        event.setNotifyStudents(true);
        return event;
    }

    @Override
    public List<Event> getAllUpcomingEvents(String studentId) {
        User student = userService.getUserByUserId(studentId);
        if (student == null) {
            throw new AppException("Invalid Student");
        }
        String batchId = batchService.getBatchIdByStudentId(studentId);
        if (batchId == null) {
            throw new AppException("Batch ID cannot be null");
        }

        // Check the batch by batchId
        Batch batch = batchService.getBatchByBatchId(batchId);
        if (batch == null) {
            throw new AppException("Batch not found for the provided batch ID");
        }

        LocalDateTime now = LocalDateTime.now();

        List<Event> upcomingEvents = eventRepository.findAllByBatchIdAndDateAfter(batchId, now);

        if (upcomingEvents.isEmpty()) {
            throw new AppException("No Events");
        }

        return upcomingEvents;
    }
    @Override
    public Boolean updateEventDetails(UpdateEventReq eventReq) {
        // finding corect id or valid admin
        User admin = userService.getUserByUserId(eventReq.getCreatorId());
        if (admin == null || admin.getUserRole().equalsIgnoreCase("student")) {
            throw new AppException("Invalid Admin");
        } else {
            // finding correct batch
            Batch batch = batchService.getBatchByBatchId(eventReq.getBatchId());
            if (batch == null) {
                throw new AppException("Invalid Batch");
            } else {
                // finding correct event Id
                Event event = eventRepository.findByEventId(eventReq.getEventId());
                if (event == null) {
                    throw new AppException("Invalid Event");
                } else {
                    event.setDate(eventReq.getDate());
                    event.setTitle(eventReq.getTitle());
                    event.setDescription(eventReq.getDescription());
                    event.setLocation(eventReq.getLocation());
                    event.setEventType(eventReq.getEventType());
                    event.setTime(eventReq.getTime());
                    event.setOrganizer(eventReq.getOrganizer());
                    eventRepository.save(event);
                    return true;
                }
            }
        }
    }

    @Override
    public List<Event> getEventsByBatchId(String batchId) {
        Batch batch = batchService.getBatchByBatchId(batchId);
        if (batch == null) {
            throw new AppException("Invalid Batch");
        } else {
            return eventRepository.findAllByBatchId(batchId);
        }
    }
}
