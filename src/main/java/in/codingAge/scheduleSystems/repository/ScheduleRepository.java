package in.codingAge.scheduleSystems.repository;

import in.codingAge.scheduleSystems.model.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ScheduleRepository extends MongoRepository<Schedule, String > {
    Schedule findByBatchIdAndDateAndTime(String batchId, Date date, Time time);

    List<Schedule> findByBatchIdIn(Set<String> batchIds);

    List<Schedule> findByBatchIdAndDate(String batchId, LocalDate date);

    Schedule findByScheduleId(String scheduleId);
}
