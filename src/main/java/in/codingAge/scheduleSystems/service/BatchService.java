package in.codingAge.scheduleSystems.service;

import in.codingAge.scheduleSystems.model.Batch;
import in.codingAge.scheduleSystems.model.request.AssignRequest;
import in.codingAge.scheduleSystems.model.request.BatchRequest;
import in.codingAge.scheduleSystems.model.request.RemoveBatchRequest;
import in.codingAge.scheduleSystems.model.request.UpdateBatchReq;

import java.util.List;

public interface BatchService {

    Batch createBatch(BatchRequest batchRequest);

    Boolean assignStudentsInBatch(AssignRequest assignRequest);

    List<Batch> getAllBatches();

    Boolean removeStudentsFromBatch(RemoveBatchRequest batchRequest);

    Batch getBatchByBatchId(String batchId);

    Batch saveUpdates(Batch batch, String userId);

    Boolean updateBatch(UpdateBatchReq updateBatchReq);

    String getBatchIdByStudentId(String studentId);

}
