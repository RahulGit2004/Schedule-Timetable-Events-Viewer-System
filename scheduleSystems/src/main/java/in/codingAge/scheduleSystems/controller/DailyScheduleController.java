package in.codingAge.scheduleSystems.controller;

import in.codingAge.scheduleSystems.base.ApiResponse;
import in.codingAge.scheduleSystems.model.DailySchedule;
import in.codingAge.scheduleSystems.model.request.DailyScheduleRequest;
import in.codingAge.scheduleSystems.service.DailyScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/daily_schedule")
@CrossOrigin("*")
public class DailyScheduleController {

    @Autowired
    private DailyScheduleService dailyScheduleService;

    @PostMapping("/dailySchedules")
    public ApiResponse<Boolean> createDailySchedule (@RequestBody DailyScheduleRequest dailyScheduleRequest) {
        return new ApiResponse<>(dailyScheduleService.createDailySchedule(dailyScheduleRequest), HttpStatus.ACCEPTED);
    }

    @GetMapping("/for/batchBy/id/and/date")
    public ApiResponse<List<DailySchedule>> getDailySchedulesByBatchIdAndDate(@RequestParam String batchId, LocalDateTime date) {
        return new ApiResponse<>(dailyScheduleService.getSchedulesByBatchIdAndDate(batchId,date), HttpStatus.ACCEPTED);
    }

    @GetMapping("/get/batchId")
    public ApiResponse<List<DailySchedule>> getDailySchedulesByBatchId(@RequestParam String batchId) {
        return new ApiResponse<>(dailyScheduleService.getSchedulesByBatchId(batchId), HttpStatus.ACCEPTED);
    }


}
