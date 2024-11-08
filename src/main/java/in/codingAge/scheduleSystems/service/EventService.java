package in.codingAge.scheduleSystems.service;

import in.codingAge.scheduleSystems.model.Event;
import in.codingAge.scheduleSystems.model.request.EventRequest;
import in.codingAge.scheduleSystems.model.request.UpdateEventReq;

import java.util.List;

public interface EventService {
    boolean createEvent(EventRequest eventRequest);

    void updateEventStatus();

    List<Event> getAllUpcomingEvents(String studentId);

    Boolean updateEventDetails(UpdateEventReq eventReq);

    List<Event> getAllUpcomingTest(String studentId);
}
