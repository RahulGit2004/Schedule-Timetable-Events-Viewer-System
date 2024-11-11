package in.codingAge.scheduleSystems.controller;

import in.codingAge.scheduleSystems.base.ApiResponse;
import in.codingAge.scheduleSystems.model.Batch;
import in.codingAge.scheduleSystems.model.User;
import in.codingAge.scheduleSystems.model.request.AssignRequest;
import in.codingAge.scheduleSystems.model.request.BatchRequest;
import in.codingAge.scheduleSystems.model.request.RemoveBatchRequest;
import in.codingAge.scheduleSystems.model.request.UpdateBatchReq;
import in.codingAge.scheduleSystems.service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/batch")
@CrossOrigin("*")
public class BatchController {

    // todo make frontend to remove students from batch;
    @Autowired
    private BatchService batchService;

    @PostMapping("/create")
    public ApiResponse<Batch> createBatch(@RequestBody BatchRequest batchRequest) {
        return new ApiResponse<>(batchService.createBatch(batchRequest), HttpStatus.ACCEPTED);
    }

    @PutMapping("/assign/students")
    public ApiResponse<Boolean> assignStudentsInBatch(@RequestBody AssignRequest assignRequest) {
        return new ApiResponse<>(batchService.assignStudentsInBatch(assignRequest),HttpStatus.ACCEPTED);
    }

    @PutMapping("/remove/students")
    public ApiResponse<Boolean> removeStudentsFromBatch(@RequestBody RemoveBatchRequest batchRequest) {
        return new ApiResponse<>(batchService.removeStudentsFromBatch(batchRequest),HttpStatus.ACCEPTED);
    }

    @GetMapping("/all/students/batchId")
    public ApiResponse<List<User>> getAllStudentsByBatchId(@RequestParam String batchId) {
        return new ApiResponse<>(batchService.getAllStudentsByBatchId(batchId),HttpStatus.ACCEPTED);
    }


// todo update batch api needed
    @PutMapping("/update")
    public ApiResponse<Boolean> updateBatch(@RequestBody UpdateBatchReq updateBatchReq) {
        return new ApiResponse<>(batchService.updateBatch(updateBatchReq),HttpStatus.ACCEPTED);
    }


    @GetMapping("/all/list")
    public List<Batch> getAllBatches() {
        return batchService.getAllBatches();
    }

    @GetMapping("/details/id")
    public ApiResponse<Batch> getDetailsByBatchId (@RequestParam String batchId) {
        return new ApiResponse<>(batchService.getDetailsByBatchId(batchId),HttpStatus.ACCEPTED);
    }


}
