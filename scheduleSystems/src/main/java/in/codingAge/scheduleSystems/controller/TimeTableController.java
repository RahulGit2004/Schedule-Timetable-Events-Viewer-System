package in.codingAge.scheduleSystems.controller;

import in.codingAge.scheduleSystems.base.ApiResponse;
import in.codingAge.scheduleSystems.model.TimeTable;
import in.codingAge.scheduleSystems.model.request.TimeTableRequest;
import in.codingAge.scheduleSystems.model.request.UpdateTimeTableReq;
import in.codingAge.scheduleSystems.service.TimeTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/time_table")
@CrossOrigin("*")
public class TimeTableController {

    @Autowired
    private TimeTableService timeTableService;

    @PostMapping("/create")
    public ApiResponse<Boolean> createTimeTableWithScheduleEntry(@RequestBody TimeTableRequest timeTableRequest) {
        return new ApiResponse<>(timeTableService.createTimeTableWithScheduleEntry(timeTableRequest), HttpStatus.ACCEPTED);
    }


    // todo not call this updateTimeTable api  {not worked yet}
    @PutMapping("/update")
    public ApiResponse<Boolean> updateTimeTable(@RequestBody UpdateTimeTableReq req) {
        return new ApiResponse<>(timeTableService.updateTimeTable(req), HttpStatus.ACCEPTED);
    }

    @GetMapping("/all")
    public List<TimeTable> getAllTimeTables() {
        return timeTableService.getAllTimeTables();
    }

    @GetMapping("/batchId")
    public ApiResponse<List<TimeTable>> getTimeTablesByBatchId(@RequestParam String batchId) {
        return new ApiResponse<>(timeTableService.getTimeTablesByBatchId(batchId), HttpStatus.ACCEPTED);
    }

    @GetMapping("/studentId")
    public ApiResponse<List<TimeTable>> getTimeTablesByStudentId(@RequestParam String studentId) {
        return new ApiResponse<>(timeTableService.getTimeTablesByStudentId(studentId),HttpStatus.ACCEPTED);
    }

    // todo make api if need in FrontEnd :- deleteTimeTableByBatchId()

}
