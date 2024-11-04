package in.codingAge.scheduleSystems.service.impl;

import in.codingAge.scheduleSystems.exception.AppException;
import in.codingAge.scheduleSystems.model.Batch;
import in.codingAge.scheduleSystems.model.User;
import in.codingAge.scheduleSystems.model.request.AssignRequest;
import in.codingAge.scheduleSystems.model.request.BatchRequest;
import in.codingAge.scheduleSystems.repository.BatchRepository;
import in.codingAge.scheduleSystems.service.BatchService;
import in.codingAge.scheduleSystems.service.UserService;
import org.apache.tomcat.websocket.BackgroundProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BatchServiceImpl implements BatchService {

    @Autowired
    private BatchRepository batchRepository;

    @Autowired
    private UserService userService;


    @Override
    public Batch createBatch(BatchRequest batchRequest) {

        // Checking if a batch with the same name exists
        Batch existingBatch = batchRepository.findByBatchName(batchRequest.getBatchName());

        if (existingBatch != null) {
            throw new AppException("Batch with same name Already Exists");
        } else {
            Batch newBatch = new Batch();
            newBatch.setBatchName(batchRequest.getBatchName());
            newBatch.setCreatorName(batchRequest.getCreatorName());
            newBatch.setCreatedDate(batchRequest.getCreatedDate());
            newBatch.setActive(true);
            // Saving new Batch in my repository
            return batchRepository.save(newBatch);
        }
    }

    @Override
    public Boolean assignStudentsInBatch(AssignRequest assignRequest) {
        // checking correct batch id or not
        Batch batch = batchRepository.findByBatchId(assignRequest.getBatchId());

        if(batch == null) {
            throw new AppException("Invalid Batch Id");
        } else {
            // checking correct admin id or not
            User user = userService.getUserByUserId(assignRequest.getCreatorId());
            if(user == null) {
                throw new AppException("Invalid Admin Id");
            } else {
                // if no student assigned
                boolean assigned = false;
                for(String student: assignRequest.getStudentId()) {
                    User newStudent = userService.getUserByUserId(student);
                    if(newStudent != null) {
                        batch.getStudentList().add(newStudent); // assign new student in batch
                        newStudent.setInBatch(true);
                        newStudent.getBatchList().add(batch); // student assigned a batch
                        userService.saveUpdates(newStudent); // saving each student in their repo
                        assigned = true; // at least one student assign
                    }
                }
                batchRepository.save(batch);
                return assigned;
            }

        }
    }

    @Override
    public List<Batch> getAllBatches() {
        return batchRepository.findAll();
    }

}
