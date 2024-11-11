package in.codingAge.scheduleSystems.service.impl;

import in.codingAge.scheduleSystems.exception.AppException;
import in.codingAge.scheduleSystems.model.Batch;
import in.codingAge.scheduleSystems.model.User;
import in.codingAge.scheduleSystems.model.request.AssignRequest;
import in.codingAge.scheduleSystems.model.request.BatchRequest;
import in.codingAge.scheduleSystems.model.request.RemoveBatchRequest;
import in.codingAge.scheduleSystems.model.request.UpdateBatchReq;
import in.codingAge.scheduleSystems.repository.BatchRepository;
import in.codingAge.scheduleSystems.service.BatchService;
import in.codingAge.scheduleSystems.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
            // checking valid userId or not
            User admin = userService.getUserByUserId(batchRequest.getCreatorId());
            // if given id is user
            if (admin == null || admin.getUserRole().equalsIgnoreCase("user")) {
                throw new AppException("You are not authorize for this action");
            }
            Batch batch = new Batch();
            batch.setCreatorId(batchRequest.getCreatorId());
            batch.setBatchName(batchRequest.getBatchName());
            batch.setCreatorName(batchRequest.getCreatorName());
            batch.setBatchDescription(batchRequest.getBatchDescription());
            batch.setCreatedDate(batchRequest.getCreatedDate());
            userService.saveUpdates(admin); // this will save updated data on Batch
            batch.setActiveBatch(true);
            // Saving new Batch in my repository
            return batchRepository.save(batch);
        }
    }

    @Override
    public Boolean assignStudentsInBatch(AssignRequest assignRequest) {
        // checking correct batch id or not
        Batch batch = getBatchByBatchId(assignRequest.getBatchId());

        if(batch == null) {
            throw new AppException("Invalid Batch Id");
        } else {
            // checking correct admin id or not
            User user = userService.getUserByUserId(assignRequest.getCreatorId());
            if(user == null) {
                throw new AppException("Invalid User ");
            } else {
                // if user is admin
                if(user.getUserRole().equalsIgnoreCase("admin")) {
                    // if no student assigned
                    boolean assigned = false;
                    for(String student: assignRequest.getStudentId()) {
                        User newStudent = userService.getUserByUserId(student);
                        if(newStudent != null) {
                            batch.getUsers().add(newStudent);
                            userService.saveUpdates(newStudent); // saving each student in their repo
                            assigned = true; // at least one student assign
                        }
                    }
                    if(!assigned) {
                        throw new AppException("No Students assign in batch");
                    }
                    batchRepository.save(batch);
                    return assigned;
                } else {
                    throw new AppException("User is not authorize for this action");
                }

            }

        }
    }

    @Override
    public List<Batch> getAllBatches() {
        try {
            return batchRepository.findAll();
        } catch (Exception e) {
            // Log the exception
            System.err.println("Error retrieving batches: " + e.getMessage());
            // Optionally, rethrow as a custom exception or return an empty list
            throw new RuntimeException("Failed to retrieve batches", e);
        }
    }

    @Override
    public Boolean removeStudentsFromBatch(RemoveBatchRequest batchRequest) {
        // batch id correct or not
        Batch batch = batchRepository.findByBatchId(batchRequest.getBatchId());
        if(batch == null) {
            throw new AppException("Invalid Batch Id");
        } else {
            // correct
            User admin = userService.getUserByUserId(batchRequest.getCreatorId());
            if(admin == null) {
                throw new AppException("Invalid Batch Id");
            } else {
                // if user is not admin
                if(admin.getUserRole().equalsIgnoreCase("admin")) {
                    boolean isRemove = false;
                    for (String studentId: batchRequest.getStudentsId()){
                        User student = userService.getUserByUserId(studentId);
                        if(student != null) {
                            batch.getUsers().remove(student);
                            userService.saveUpdates(student);
                            isRemove = true;
                        }
                    }
                    // If no students were removed it may indicate an invalid student list
                    if (!isRemove) {
                        throw new AppException("No valid students found to remove");
                    }
                    batchRepository.save(batch);
                    return isRemove;
                } else {
                    throw new AppException("Invalid User for this action");
                }
            }
        }
    }

    @Override
    public Batch getBatchByBatchId(String batchId) {
        return batchRepository.findByBatchId(batchId);
    }


    @Override
    public Batch saveUpdates(Batch batch) {
        return batchRepository.save(batch);
    }


    @Override
    public Boolean updateBatch(UpdateBatchReq updateBatchReq) {
        User admin = userService.getUserByUserId(updateBatchReq.getCreatorId());
        if (admin == null || admin.getUserRole().equalsIgnoreCase("user")) {
            throw new AppException("You are not authorize for this action");
        } else {
            Batch batch = batchRepository.findByBatchId(updateBatchReq.getBatchId());
            if (batch == null) {
                throw new AppException("Invalid Batch Id");
            } else {
                batch.setBatchDescription(updateBatchReq.getBatchDescription());
                batch.setBatchName(updateBatchReq.getBatchName());
                batch.setCreatedDate(updateBatchReq.getCreatedDate());
                batch.setCreatorName(updateBatchReq.getCreatorName());
                batchRepository.save(batch);
                return true;
            }
        }
    }

    @Override
    public String getBatchIdByStudentId(String studentId) {
       for (Batch batch: getAllBatches()){
           for (User student : batch.getUsers()) {
               if(student.getUserId().equals(studentId)) {
                   return batch.getBatchId();
               }
           }
       }
       throw new AppException("Student is not in Batch");
    }

    @Override
    public List<User> getAllStudentsByBatchId(String batchId) {
        Batch batch = batchRepository.findByBatchId(batchId);
        if (batch == null) {
            throw new AppException("Invalid Batch");
        } else {
            List<User> students = new ArrayList<>();
            if (batch.getUsers() == null || batch.getUsers().isEmpty()) {
                throw new AppException("No one in Batch");
            }
            for (User user : batch.getUsers()) {
                if (user != null && user.getUserRole().equalsIgnoreCase("student")) {
                    students.add(user);
                }
            }
            if (students.isEmpty()) {
                throw new AppException("No Students in Batch");
            }
            return students;
        }
    }


    @Override
    public Batch getDetailsByBatchId(String batchId) {
        Batch batch = getBatchByBatchId(batchId);
        if (batch == null) {
            throw new AppException("Invalid Batchid");
        } else {
            return batch;
        }
    }


}
