package in.codingAge.scheduleSystems.repository;

import in.codingAge.scheduleSystems.model.DailySchedule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DailyScheduleRepository extends MongoRepository<DailySchedule, String> {
    List<DailySchedule> findAllByBatchIdAndDate(String batchId, LocalDateTime date);

    List<DailySchedule> findAllByBatchId(String batchId);
}
