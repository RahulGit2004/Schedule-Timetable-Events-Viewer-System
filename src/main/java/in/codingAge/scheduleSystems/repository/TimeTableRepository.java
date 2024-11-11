package in.codingAge.scheduleSystems.repository;

import in.codingAge.scheduleSystems.model.TimeTable;
import in.codingAge.scheduleSystems.model.request.TimeTableRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeTableRepository extends MongoRepository<TimeTable,String > {
    TimeTable findByBatchId(String batchId);
    List<TimeTable> findAllByBatchId(String batchId);
    TimeTable findByTimeTableId(String timeTableId);
}
