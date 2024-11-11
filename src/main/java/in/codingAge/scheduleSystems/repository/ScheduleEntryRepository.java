package in.codingAge.scheduleSystems.repository;

import in.codingAge.scheduleSystems.model.ScheduleEntry;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ScheduleEntryRepository extends MongoRepository<ScheduleEntry,String> {
    List<ScheduleEntry> findByTimetableIdAndStartTime(String timetableId, Time startTime);

    ScheduleEntry findByScheduleEntryId(String scheduleEntryId);
}
