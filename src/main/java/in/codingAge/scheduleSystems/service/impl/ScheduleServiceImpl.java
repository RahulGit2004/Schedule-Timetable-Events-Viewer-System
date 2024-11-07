package in.codingAge.scheduleSystems.service.impl;

import in.codingAge.scheduleSystems.exception.AppException;
import in.codingAge.scheduleSystems.model.Batch;
import in.codingAge.scheduleSystems.model.Schedule;
import in.codingAge.scheduleSystems.model.User;
import in.codingAge.scheduleSystems.model.request.CreateScheduleReq;
import in.codingAge.scheduleSystems.repository.ScheduleRepository;
import in.codingAge.scheduleSystems.service.BatchService;
import in.codingAge.scheduleSystems.service.ScheduleService;
import in.codingAge.scheduleSystems.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private BatchService batchService;

    @Autowired
    private UserService userService;

    @Override
    public Boolean createSchedule(CreateScheduleReq daily, String eventType) {
        // Validate batch existence
        Batch batch = batchService.getBatchByBatchId(daily.getBatchId());
        if (batch == null) {
            throw new AppException("Invalid Batch ID: No batch found with the provided ID.");
        }

        // Validate schedule conflict with batch
        List<Schedule> existingSchedules = scheduleRepository.findByBatchIdAndDate(
                daily.getBatchId(), daily.getDate());

        // Check for overlapping schedules within the same batch
        LocalTime newStartTime = daily.getTime();
        LocalTime newEndTime = newStartTime.plus(daily.getDuration());
        for (Schedule existingSchedule : existingSchedules) {
            LocalTime existingStartTime = existingSchedule.getTime();
            LocalTime existingEndTime = existingStartTime.plus(existingSchedule.getDuration());

            if (!(newEndTime.isBefore(existingStartTime) || newStartTime.isAfter(existingEndTime))) {
                throw new AppException("Schedule conflict: A class already exists at the specified time for this batch.");
            }
        }

        // Validate creator as admin
        User admin = userService.getUserByUserId(daily.getCreatorId());
        if (admin == null || !admin.getUserRole().equalsIgnoreCase("admin")) {
            throw new AppException("Invalid admin: Creator must have admin privileges.");
        }
        Schedule schedule = new Schedule();
        schedule.setBatchId(daily.getBatchId());
        schedule.setCreatorId(daily.getCreatorId());
        schedule.setEventType(eventType);
        schedule.setDate(daily.getDate());
        schedule.setTime(daily.getTime());
        schedule.setDuration(daily.getDuration());
        schedule.setClassName(daily.getClassName());
        schedule.setInstructorName(daily.getInstructorName());
        schedule.setLocation(daily.getLocation());
        schedule.setStatus("Scheduled");

        // Update batch with new schedule and save updates
        batch.getSchedules().add(schedule);
        batchService.saveUpdates(batch, admin.getUserId());

        // Save schedule in repository
        scheduleRepository.save(schedule);
        return true;
    }


    @Override
    public Boolean createTestSchedule(CreateScheduleReq test, String eventType) {
        return createSchedule(test, eventType);
    }

    @Override
    public Boolean createEventSchedule(CreateScheduleReq event, String eventType) {
        return createSchedule(event, eventType);
    }

    @Override
    public List<Schedule> getAllDailyScheduleByStudentId(String studentId) {
//        my written code
//        User student = userService.getUserByUserId(studentId);
//        List<Schedule> scheduleList = new ArrayList<>();
//        if(student == null) {
//            throw new AppException("Invalid Student");
//        } else {
//            List<Schedule> schedules = scheduleRepository.findAll();
//            for (Batch batch : student.getBatchList()) {
//                for (Schedule schedule: schedules) {
//                    if(batch.getBatchId().equals(schedule.getBatchId())) {
//                        scheduleList.add(schedule);
//                    }
//                }
//            }
//        }
//
//        return scheduleList;

        // ai written
        // Retrieve the student
        User student = userService.getUserByUserId(studentId);
        if (student == null) {
            throw new AppException("Invalid Student");
        }

        // Get all batch IDs associated with the student
        Set<String> batchIds = student.getBatchList().stream()
                .map(Batch::getBatchId)
                .collect(Collectors.toSet());

        // Fetch all schedules for the batches associated with the student
        return scheduleRepository.findByBatchIdIn(batchIds);
    }

    @Override
    public List<Schedule> getDailyTimeTableByStudentId(String studentId) {
        User student = userService.getUserByUserId(studentId);
        if (student == null || student.getUserRole().equalsIgnoreCase("admin")) {
            throw new AppException("Invalid Student");
        } else {
            String batchId = batchService.getBatchIdByStudentId(studentId);
            if (batchId == null) {
                throw new AppException("Batch Id can not be null");
            } else {
                LocalDate date = LocalDate.now(); // helps to find current Date
                return scheduleRepository.findByBatchIdAndDate(batchId, date);
            }
        }
    }

    @Override
    public Boolean updateExistsSchedule(Schedule scheduleReq) {
        Schedule schedule = scheduleRepository.findByScheduleId(scheduleReq.getScheduleId());
        if (schedule == null) {
            throw new AppException("Invalid Schedule Id");
        } else {
            User admin = userService.getUserByUserId(scheduleReq.getCreatorId());
            if (admin == null || admin.getUserRole().equalsIgnoreCase("student")) {
                throw new AppException("You are not Authorize");
            } else {
                schedule.setTime(scheduleReq.getTime());
                schedule.setDate(scheduleReq.getDate());
                schedule.setBatchId(scheduleReq.getBatchId());
                schedule.setLocation(scheduleReq.getLocation());
                schedule.setInstructorName(scheduleReq.getInstructorName());
                schedule.setClassName(scheduleReq.getClassName());
                schedule.setEventType(scheduleReq.getEventType());
                scheduleRepository.save(schedule);
                return true;
            }
        }
    }
}
