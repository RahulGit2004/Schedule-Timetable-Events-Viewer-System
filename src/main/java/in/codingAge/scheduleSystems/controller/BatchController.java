package in.codingAge.scheduleSystems.controller;

import in.codingAge.scheduleSystems.base.ApiResponse;
import in.codingAge.scheduleSystems.model.Batch;
import in.codingAge.scheduleSystems.model.request.AssignRequest;
import in.codingAge.scheduleSystems.model.request.BatchRequest;
import in.codingAge.scheduleSystems.service.BatchService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/batch")
public class BatchController {

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

    @GetMapping("/all/list")
    public List<Batch> getAllBatches() {
        return batchService.getAllBatches();
    }

}
