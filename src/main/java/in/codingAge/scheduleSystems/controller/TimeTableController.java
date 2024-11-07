package in.codingAge.scheduleSystems.controller;

import in.codingAge.scheduleSystems.base.ApiResponse;
import in.codingAge.scheduleSystems.model.TimeTable;
import in.codingAge.scheduleSystems.model.request.TimeTableRequest;
import in.codingAge.scheduleSystems.model.request.UpdateTimeTableReq;
import in.codingAge.scheduleSystems.model.request.schedule.ScheduleTimeTableRequest;
import in.codingAge.scheduleSystems.model.response.ScheduledTimeTableResponse;
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
    public ApiResponse<Boolean> createTimeTable(@RequestBody TimeTableRequest timeTableRequest) {
        return new ApiResponse<>(timeTableService.createTimeTable(timeTableRequest), HttpStatus.ACCEPTED);
    }

    @PostMapping("/schedule")
    public ApiResponse<ScheduledTimeTableResponse> scheduleTimeTable(@RequestBody ScheduleTimeTableRequest request) {
        return new ApiResponse<>(timeTableService.scheduleTimeTable(request), HttpStatus.ACCEPTED);
    }

    @PutMapping("/update")
    public ApiResponse<Boolean> updateTimeTable(@RequestBody UpdateTimeTableReq req) {
        return new ApiResponse<>(timeTableService.updateTimeTable(req), HttpStatus.ACCEPTED);
    }

    @GetMapping("/all")
    public List<TimeTable> getAllTimeTables() {
        return timeTableService.getAllTimeTables();
    }

    @GetMapping("/batchId")
    public ApiResponse<TimeTable> getTimeTableByBatchId(String batchId) {
        return new ApiResponse<>(timeTableService.getTimeTableByBatchId(batchId), HttpStatus.ACCEPTED);
    }

    // todo make api if need in FrontEnd :- deleteTimeTableByBatchId()

}
