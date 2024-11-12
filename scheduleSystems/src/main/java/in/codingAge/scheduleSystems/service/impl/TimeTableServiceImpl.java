package in.codingAge.scheduleSystems.service.impl;

import in.codingAge.scheduleSystems.exception.AppException;
import in.codingAge.scheduleSystems.model.*;
import in.codingAge.scheduleSystems.model.request.ScheduleEntryReq;
import in.codingAge.scheduleSystems.model.request.TimeTableRequest;
import in.codingAge.scheduleSystems.model.request.UpdateTimeTableReq;
import in.codingAge.scheduleSystems.repository.TimeTableRepository;
import in.codingAge.scheduleSystems.service.BatchService;
import in.codingAge.scheduleSystems.service.ScheduleEntryService;
import in.codingAge.scheduleSystems.service.TimeTableService;
import in.codingAge.scheduleSystems.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TimeTableServiceImpl implements TimeTableService {

    @Autowired
    private TimeTableRepository timeTableRepository;

    @Autowired
    private BatchService batchService;

    @Autowired
    @Lazy
    private ScheduleEntryService scheduleEntryService;

    @Autowired
    private UserService userService;


    @Override
    public Boolean createTimeTableWithScheduleEntry(TimeTableRequest timeTableRequest) {
        User admin = userService.getUserByUserId(timeTableRequest.getCreatorId());
        if (admin == null || !admin.getUserRole().equalsIgnoreCase("admin")) {
            throw new AppException("Invalid admin");
        }

        Batch batch = batchService.getBatchByBatchId(timeTableRequest.getBatchId());
        if (batch == null) {
            throw new AppException("Invalid Batch");
        }

        // Creating time table
        TimeTable timeTable = new TimeTable();
        timeTable.setCreatorId(timeTableRequest.getCreatorId());
        timeTable.setBatchId(timeTableRequest.getBatchId());
        timeTable.setDate(timeTableRequest.getDate());



        boolean isSchedule = false;

        for (ScheduleEntryReq scheduleEntryReq : timeTableRequest.getCreateScheduleReqList()) {
            // new time table id is set
//            scheduleEntryReq.setTimetableId(newTimeTable.getTimeTableId());

            ScheduleEntry scheduleEntry = scheduleEntryService.createScheduleEntry(scheduleEntryReq);
            TimeTable newTimeTable = timeTableRepository.save(timeTable);
            scheduleEntry.setTimetableId(newTimeTable.getTimeTableId());
            scheduleEntryService.saveUpdates(scheduleEntry);
            batch.getTimeTables().add(newTimeTable);
            // Add to the schedule entry in list
            newTimeTable.getScheduleEntries().add(scheduleEntry);
            isSchedule = true;
        }

        if (!isSchedule) {
            throw new AppException("No Entry for Schedule");
        }

        // Saving updated time table in repository
//        timeTableRepository.save(newTimeTable);
         timeTableRepository.save(timeTable);
        batchService.saveUpdates(batch); // Saving updated data on batch with time table

        return true;
    }

    // todo this is not completed

    @Override
    public Boolean updateTimeTable(UpdateTimeTableReq req) {
        // checking correct batch Id
        TimeTable timeTable = timeTableRepository.findByBatchId(req.getBatchId());
        if (timeTable == null) {
            throw new AppException("Invalid Batch Id");
        } else {
//            timeTable.setEventType(req.getEventType());
//            timeTable.setStartTime(req.getStartTime());
//            timeTable.setEndTime(req.getEndTime());
//            timeTable.setUpdatedAt(req.getUpdatedAt());
//            timeTable.setInstructor(req.getInstructor());
            timeTableRepository.save(timeTable);
            return true;
        }
    }

    @Override
    public TimeTable getTimeTableByTimeTableId(String timeTableId) {
        return timeTableRepository.findByTimeTableId(timeTableId);
    }

    @Override
    public List<TimeTable> getTimeTablesByStudentId(String studentId) {
        User student = userService.getUserByUserId(studentId);
        if (student == null) {
            throw new AppException("Invalid Student");
        } else {

            String batchId = batchService.getBatchIdByStudentId(studentId);
            if (batchId == null) {
                throw new AppException("Batch Id can not be null");
            } else {
                return timeTableRepository.findAllByBatchId(batchId);
            }
        }
    }

    @Override
    public List<TimeTable> getTimeTableByBatchId(String batchId) {
        return timeTableRepository.findAllByBatchId(batchId);
    }


    @Override
    public List<TimeTable> getAllTimeTables() {
        return timeTableRepository.findAll();
    }

    @Override
    public List<TimeTable> getTimeTablesByBatchId(String batchId) {
        Batch batch = batchService.getBatchByBatchId(batchId);
        if (batch == null) {
            throw new AppException("Invalid Batch");
        } else {
            List<TimeTable> timeTables = timeTableRepository.findAllByBatchId(batchId);;
            if (timeTables.isEmpty()) {
                throw new AppException("No timeTable found");
            }
            return timeTables;
        }
    }
}
