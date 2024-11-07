package in.codingAge.scheduleSystems.service.impl;

import in.codingAge.scheduleSystems.exception.AppException;
import in.codingAge.scheduleSystems.model.Batch;
import in.codingAge.scheduleSystems.model.TimeTable;
import in.codingAge.scheduleSystems.model.User;
import in.codingAge.scheduleSystems.model.request.TimeTableRequest;
import in.codingAge.scheduleSystems.model.request.UpdateTimeTableReq;
import in.codingAge.scheduleSystems.model.request.schedule.DaySchedule;
import in.codingAge.scheduleSystems.model.request.schedule.ScheduleTimeTableRequest;
import in.codingAge.scheduleSystems.model.request.schedule.Slot;
import in.codingAge.scheduleSystems.model.response.ScheduledTimeTableResponse;
import in.codingAge.scheduleSystems.repository.TimeTableRepository;
import in.codingAge.scheduleSystems.service.BatchService;
import in.codingAge.scheduleSystems.service.TimeTableService;
import in.codingAge.scheduleSystems.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TimeTableServiceImpl implements TimeTableService {

    @Autowired
    private TimeTableRepository timeTableRepository;

    @Autowired
    private BatchService batchService;

    @Autowired
    private UserService userService;


    @Override
    public Boolean createTimeTable(TimeTableRequest timeTableRequest) {
        // checking duplicate timetable
        boolean isFound = false;
        for (TimeTable timeTable : getAllTimeTables()) {
            if (timeTable.getStartTime().equals(timeTableRequest.getStartTime())) {
                isFound = true;
                break;
            }
        }
        if (isFound) {
            throw new AppException("This time is Already Registered");
        } else {
            User admin = userService.getUserByUserId(timeTableRequest.getCreatorId());
            if (admin == null) {
                throw new AppException("Invalid admin");
            }
            TimeTable timeTable = new TimeTable();
            timeTable.setBatchId(timeTableRequest.getBatchId());
            timeTable.setCreatorId(timeTableRequest.getCreatorId());
            timeTable.setInstructor(timeTableRequest.getInstructor());
            timeTable.setStartTime(timeTableRequest.getStartTime());
            timeTable.setEndTime(timeTableRequest.getEndTime());
            timeTable.setCreatedAt(timeTableRequest.getCreatedAt());
            timeTableRepository.save(timeTable);
            Batch batch = batchService.getBatchByBatchId(timeTableRequest.getBatchId());
            batch.getTimeTables().add(timeTable); // adding timetable on batch
            batchService.saveUpdates(batch,admin.getUserId());
            return true;
        }
    }

    // todo i need know how to use this snippet of code in UI
    @Override
    public ScheduledTimeTableResponse scheduleTimeTable(ScheduleTimeTableRequest request) {
        // Check for conflicts in the schedule
        boolean isConflict = hasConflictSchedule(request);
        if (isConflict) {
            throw new AppException("Schedule Conflicted");
        }

        // Prepare the TimeTable entity to be saved
        TimeTable timeTable = new TimeTable();
        timeTable.setBatchId(request.getBatchId());
        // Assuming you need to set the date, start time, end time, etc. for each slot
        for (DaySchedule daySchedule : request.getSchedules()) {
            for (Slot slot : daySchedule.getSlots()) {
                // You can set the details like date, startTime, endTime, etc. as needed
                timeTable.setDate(new Date()); // Set the current date or appropriate date
                timeTable.setStartTime(slot.getStartTime());
                timeTable.setEndTime(slot.getEndTime());
                timeTable.setInstructor(slot.getInstructor());
                timeTable.setEventType(slot.getTopic()); // Assuming eventType can be the topic

                // Save the timetable for each slot (adjust logic based on your needs)
                timeTableRepository.save(timeTable);
            }
        }

        // Prepare the response
        ScheduledTimeTableResponse response = new ScheduledTimeTableResponse();
        response.setTable(timeTable);
        response.setRequest(request);
        return response;
    }

    // this helps to find conflicts
    @Override
    public Boolean hasConflictSchedule(ScheduleTimeTableRequest request) {
        for (DaySchedule schedule : request.getSchedules()) { // monday
            List<Slot> slots = schedule.getSlots(); // monday classes slot
            for (int i = 0; i < slots.size(); i++) {
                Slot slot1 = slots.get(i);  // take a solt
                for (int j = i + 1; j < slots.size(); j++) {
                    Slot slot2 = slots.get(j); // next index slot // 1
                    // overlapping of Slots
                    if (slot1.getStartTime().compareTo(slot2.getEndTime()) < 0 &&
                            slot1.getEndTime().compareTo(slot2.getStartTime()) > 0) {
                        return true; // if conflicts found
                    }
                }
            }
        }
        return false; // No conflicts found
    }

    @Override
    public TimeTable getTimeTableByBatchId(String batchId) {
        TimeTable timeTable = timeTableRepository.findByBatchId(batchId);
        if(timeTable == null) {
            throw new AppException("Invalid BatchId");
        } else {
            return timeTable;
        }
    }

    @Override
    public Boolean updateTimeTable(UpdateTimeTableReq req) {
        // checking correct batch Id
        TimeTable timeTable = timeTableRepository.findByBatchId(req.getBatchId());
        if(timeTable == null) {
            throw new AppException("Invalid Batch Id");
        } else {
            timeTable.setEventType(req.getEventType());
            timeTable.setStartTime(req.getStartTime());
            timeTable.setEndTime(req.getEndTime());
            timeTable.setUpdatedAt(req.getUpdatedAt());
            timeTable.setInstructor(req.getInstructor());
            timeTableRepository.save(timeTable);
            return true;
        }
    }


    @Override
    public List<TimeTable> getAllTimeTables() {
        return timeTableRepository.findAll();
    }
}
