package in.codingAge.scheduleSystems.repository;

import in.codingAge.scheduleSystems.model.Batch;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatchRepository extends MongoRepository<Batch, String > {

    Batch findByBatchName(String batchName);

    Batch findByBatchId(String batchId);

//    List<Schedule> findAllByBatchId(String batchId);
}
