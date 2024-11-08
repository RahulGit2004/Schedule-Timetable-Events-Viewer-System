package in.codingAge.scheduleSystems.repository;

import in.codingAge.scheduleSystems.model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends MongoRepository<Event, String> {
    List<Event> findByIsActiveTrue();

    Event findByEventId(String eventId);
}
