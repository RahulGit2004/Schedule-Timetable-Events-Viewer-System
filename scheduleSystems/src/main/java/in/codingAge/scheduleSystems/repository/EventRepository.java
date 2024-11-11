package in.codingAge.scheduleSystems.repository;

import in.codingAge.scheduleSystems.model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface EventRepository extends MongoRepository<Event, String> {
    Event findByEventId(String eventId);

    List<Event> findAllByBatchId(String batchId);

    List<Event> findAllByBatchIdAndDateAfter(String batchId, LocalDate date);

    List<Event> findByBatchIdAndDate(String batchId, LocalDate date);
}

