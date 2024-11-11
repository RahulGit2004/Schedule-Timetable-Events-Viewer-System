package in.codingAge.scheduleSystems.controller;

import in.codingAge.scheduleSystems.base.ApiResponse;
import in.codingAge.scheduleSystems.model.ScheduleEntry;
import in.codingAge.scheduleSystems.service.ScheduleEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/schedule_entry")
@CrossOrigin("*")
public class ScheduleEntryController {

    @Autowired
    private ScheduleEntryService scheduleEntryService;

    // i have used this api when timeTable is created
//    public ApiResponse<ScheduleEntry> createScheduleEntry(@RequestBody ScheduleEntryReq scheduleEntryReq) {
//        return new ApiResponse<>(scheduleEntryService.createScheduleEntry(scheduleEntryReq), HttpStatus.ACCEPTED);
//    }

    @GetMapping("/get")
    public ApiResponse<ScheduleEntry> getScheduleEntryById(@RequestParam String scheduleEntryId) {
        return new ApiResponse<>(scheduleEntryService.getScheduleEntryById(scheduleEntryId),HttpStatus.ACCEPTED);
    }

    @PutMapping("/update")
    public ApiResponse<Boolean> updateScheduleEntry(@RequestBody ScheduleEntry scheduleEntry) {
        return new ApiResponse<>(scheduleEntryService.updateScheduleEntry(scheduleEntry),HttpStatus.ACCEPTED);
    }

    @GetMapping("/all")
    public List<ScheduleEntry> getAll(){
        return scheduleEntryService.getAll();
    }




}
