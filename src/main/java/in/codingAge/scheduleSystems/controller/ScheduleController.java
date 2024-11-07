package in.codingAge.scheduleSystems.controller;

import in.codingAge.scheduleSystems.base.ApiResponse;
import in.codingAge.scheduleSystems.model.Schedule;
import in.codingAge.scheduleSystems.model.request.CreateScheduleReq;
import in.codingAge.scheduleSystems.service.ScheduleService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule")
@CrossOrigin("*")
public class ScheduleController {
    // todo make api, createClassSchedule, createTestSchedule, createEventSchedule

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping("/create/daily")
    public ApiResponse<Boolean> createDailySchedule(@RequestBody CreateScheduleReq daily, @PathVariable String eventType) {
        return new ApiResponse<>(scheduleService.createSchedule(daily,eventType), HttpStatus.ACCEPTED);
    }

    @PostMapping("/create/event")
    public ApiResponse<Boolean> createEventSchedule(@RequestBody CreateScheduleReq event, @PathVariable String eventType) {
        return new ApiResponse<>(scheduleService.createEventSchedule(event,eventType), HttpStatus.ACCEPTED);
    }
    @PostMapping("/create/test")
    public ApiResponse<Boolean> createTestSchedule(@RequestBody CreateScheduleReq test, @PathVariable String eventType) {
        return new ApiResponse<>(scheduleService.createTestSchedule(test,eventType), HttpStatus.ACCEPTED);
    }

    @GetMapping("/all/daily")
    public ApiResponse<List<Schedule>> getAllDailyScheduleByStudentId(@RequestParam String studentId) {
        return new ApiResponse<>(scheduleService.getAllDailyScheduleByStudentId(studentId),HttpStatus.ACCEPTED);
    }

    @GetMapping("/all/timetable")
    public ApiResponse<List<Schedule>> getDailyTimeTableByStudentId(@RequestParam String studentId) {
        return new ApiResponse<>(scheduleService.getDailyTimeTableByStudentId(studentId),HttpStatus.ACCEPTED);
    }

    @PutMapping("/update/exists")
    public ApiResponse<Boolean> updateExistsSchedule(@RequestBody Schedule schedule) {
        return new ApiResponse<>(scheduleService.updateExistsSchedule(schedule),HttpStatus.ACCEPTED);
    }





}
