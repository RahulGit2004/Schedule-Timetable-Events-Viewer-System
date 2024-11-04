package in.codingAge.scheduleSystems.service;

import in.codingAge.scheduleSystems.model.Batch;
import in.codingAge.scheduleSystems.model.request.AssignRequest;
import in.codingAge.scheduleSystems.model.request.BatchRequest;

import java.util.List;

public interface BatchService {

    Batch createBatch(BatchRequest batchRequest);

    Boolean assignStudentsInBatch(AssignRequest assignRequest);

    List<Batch> getAllBatches();
}
