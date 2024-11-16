package in.codingAge.scheduleSystems.service.impl;

import in.codingAge.scheduleSystems.exception.AppException;
import in.codingAge.scheduleSystems.model.*;
import in.codingAge.scheduleSystems.model.request.DailyScheduleRequest;
import in.codingAge.scheduleSystems.model.request.ScheduleEntryReq;
import in.codingAge.scheduleSystems.repository.DailyScheduleRepository;
import in.codingAge.scheduleSystems.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DailyScheduleServiceImpl implements DailyScheduleService {

    @Autowired
    private DailyScheduleRepository dailyScheduleRepository;

    @Autowired
    private TimeTableService timeTableService;

    @Autowired
    private UserService userService;

    @Autowired
    private BatchService batchService;

    @Autowired
    private ScheduleEntryService scheduleEntryService;


    @Override
    public Boolean createDailySchedule(DailyScheduleRequest dailyScheduleRequest) {
        User admin = userService.getUserByUserId(dailyScheduleRequest.getCreatorId());
        if (admin == null || !admin.getUserRole().equalsIgnoreCase("admin")) {
            throw new AppException("Invalid Admin");
        }
        Batch batch = batchService.getBatchByBatchId(dailyScheduleRequest.getBatchId());
        if (batch == null) {
            throw new AppException("Invalid Batch");
        }

        List<TimeTable> timeTable = timeTableService.getTimeTableByBatchId(dailyScheduleRequest.getBatchId());

        /// if a batch have no time table we can not create daily schedule of that batch
        if (timeTable.isEmpty()) {
            throw new AppException("No Time table found for batch");
        }

        DailySchedule dailySchedule = new DailySchedule();
        dailySchedule.setBatchId(dailyScheduleRequest.getBatchId());
        dailySchedule.setCreatorId(dailyScheduleRequest.getCreatorId());
        dailySchedule.setDate(dailyScheduleRequest.getDate());

        boolean isScheduleCreated = false;
        for (ScheduleEntryReq scheduleEntryReq : dailyScheduleRequest.getEvents()) {
          ScheduleEntry scheduleEntry = scheduleEntryService.createScheduleEntry(scheduleEntryReq);
          dailySchedule.setTimeTableId(scheduleEntry.getTimetableId());
          dailySchedule.getEvents().add(scheduleEntry);
          dailyScheduleRepository.save(dailySchedule); // saved every data on database
            isScheduleCreated = true;
        }
        if (!isScheduleCreated) {
            throw new AppException("No Entries for Schedule");
        }
        return true;
    }

    @Override
    public List<DailySchedule> getSchedulesByBatchIdAndDate(String batchId, LocalDateTime date) {
        Batch batch = batchService.getBatchByBatchId(batchId);
        if (batch == null) {
            throw new AppException("Invalid Batch Id");
        }
        return dailyScheduleRepository.findAllByBatchIdAndDate(batchId,date);
    }

    @Override
    public List<DailySchedule> getSchedulesByBatchId(String batchId) {
        Batch batch = batchService.getBatchByBatchId(batchId);
        if (batch == null) {
            throw new AppException("Invalid Batch Id");
        } else {
            return dailyScheduleRepository.findAllByBatchId(batchId);
        }
    }
}
