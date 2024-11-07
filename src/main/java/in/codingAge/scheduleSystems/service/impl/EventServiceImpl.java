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
import java.util.ArrayList;
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
            if (admin.getUserRole().equalsIgnoreCase("student")) {
                throw new AppException("You are not authorize");
            } else {
                Event event = getEvent(eventRequest);
                eventRepository.save(event);
                batch.getEvents().add(event);
                batchService.saveUpdates(batch, admin.getUserId());
                return true;
            }
        }
    }

    private static Event getEvent(EventRequest eventRequest) {
        Event event = new Event();
        event.setBatchId(event.getBatchId());
        event.setEventDate(eventRequest.getEventDate());
        event.setEventStartTime(eventRequest.getEventStartTime());
        event.setEventEndTime(eventRequest.getEventEndTime());
        event.setEventDescription(event.getEventDescription());
        event.setLocation(event.getLocation());
        event.setStatus(event.getStatus());
        event.setEventName(event.getEventName());
        event.setEventOrganizer(event.getEventOrganizer());
        event.setActive(true);
        return event;
    }

    @Override
    @Scheduled(fixedRate = 3600000)
    public void updateEventStatus() {
        List<Event> activeEvents = eventRepository.findByIsActiveTrue(); // Fetch all active events
        LocalDateTime now = LocalDateTime.now(); // Current time
        for (Event event : activeEvents) {
            LocalDateTime eventEndTime = event.getEventEndTime();
            // Check if the event end time has passed
            if (eventEndTime.isBefore(now)) {
                event.setActive(false);
                eventRepository.save(event);
            }
        }
    }

    @Override
    public List<Event> getAllUpcomingEvents(String studentId) {
        User student = userService.getUserByUserId(studentId);
        if (student == null || student.getUserRole().equalsIgnoreCase("admin")) {
            throw new AppException("Invalid Student");
        } else {
            String batchId = batchService.getBatchIdByStudentId(studentId);
            if (batchId == null) {
                throw new AppException("Batch ID cannot be null");
            } else {

                // Check the batch by batchId
                Batch batch = batchService.getBatchByBatchId(batchId);
                if (batch == null) {
                    throw new AppException("Batch not found for the provided batch ID");
                }

                LocalDate today = LocalDate.now();
                List<Event> upcomingEvents = new ArrayList<>();
                for (Event event : batch.getEvents()) {
                    LocalDate eventDate = event.getEventDate().toLocalDate();
                    // Check if the event date is today or in the future
                    if (eventDate.isAfter(today) || eventDate.isEqual(today)) {
                        upcomingEvents.add(event);
                    }
                }
                return upcomingEvents;
            }
        }
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
                    event.setEventDate(eventReq.getEventDate());
                    event.setEventName(event.getEventName());
                    event.setEventDescription(event.getEventDescription());
                    event.setEventStartTime(event.getEventStartTime());
                    eventReq.setEventEndTime(event.getEventEndTime());
                    event.setEventOrganizer(event.getEventOrganizer());
                    eventRepository.save(event);
                    return true;
                }
            }
        }
    }

    @Override
    public List<Event> getAllUpcomingTest(String studentId) {
        User student = userService.getUserByUserId(studentId);
        if (student == null || student.getUserRole().equalsIgnoreCase("admin")) {
            throw new AppException("Invalid Student");
        } else {
            String batchId = batchService.getBatchIdByStudentId(studentId);
            if (batchId == null) {
                throw new AppException("Batch ID cannot be null");
            }
            else {
                // Check the batch by batchId
                Batch batch = batchService.getBatchByBatchId(batchId);
                if (batch == null) {
                    throw new AppException("Batch not found for the provided batch ID");
                }

                LocalDate today = LocalDate.now();
                List<Event> upcomingTests = new ArrayList<>();
                for (Event event : batch.getEvents()) {
                    LocalDate testDate = event.getEventDate().toLocalDate();
                    // Check if the event date is today or in the future
                    if (testDate.isAfter(today) || testDate.isEqual(today)) {
                        upcomingTests.add(event);
                    }
                }
                return upcomingTests;
            }
        }
    }
}
