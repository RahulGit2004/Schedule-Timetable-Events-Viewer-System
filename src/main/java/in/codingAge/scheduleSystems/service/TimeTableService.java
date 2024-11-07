package in.codingAge.scheduleSystems.service;

import in.codingAge.scheduleSystems.model.TimeTable;
import in.codingAge.scheduleSystems.model.request.TimeTableRequest;
import in.codingAge.scheduleSystems.model.request.UpdateTimeTableReq;
import in.codingAge.scheduleSystems.model.request.schedule.ScheduleTimeTableRequest;
import in.codingAge.scheduleSystems.model.response.ScheduledTimeTableResponse;

import java.util.List;

public interface TimeTableService {
    Boolean createTimeTable(TimeTableRequest timeTableRequest);

    List<TimeTable> getAllTimeTables();

    ScheduledTimeTableResponse scheduleTimeTable(ScheduleTimeTableRequest request);

    Boolean hasConflictSchedule(ScheduleTimeTableRequest request);

    TimeTable getTimeTableByBatchId(String batchId);

    Boolean updateTimeTable(UpdateTimeTableReq req);
}
