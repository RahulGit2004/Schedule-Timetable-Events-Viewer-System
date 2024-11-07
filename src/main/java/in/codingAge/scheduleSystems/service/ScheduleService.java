package in.codingAge.scheduleSystems.service;

import in.codingAge.scheduleSystems.model.Schedule;
import in.codingAge.scheduleSystems.model.request.CreateScheduleReq;

import java.util.List;

public interface ScheduleService {
    Boolean createSchedule(CreateScheduleReq req, String eventType);

    Boolean createTestSchedule(CreateScheduleReq req, String eventType);

    Boolean createEventSchedule(CreateScheduleReq req, String eventType);

    List<Schedule> getAllDailyScheduleByStudentId(String studentId);

    List<Schedule> getDailyTimeTableByStudentId(String studentId);

    Boolean updateExistsSchedule(Schedule schedule);
}
