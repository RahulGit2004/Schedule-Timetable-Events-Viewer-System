package in.codingAge.scheduleSystems.service;

import in.codingAge.scheduleSystems.model.TimeTable;
import in.codingAge.scheduleSystems.model.request.TimeTableRequest;
import in.codingAge.scheduleSystems.model.request.UpdateTimeTableReq;

import java.util.List;

public interface TimeTableService {
    Boolean createTimeTableWithScheduleEntry(TimeTableRequest timeTableRequest);

    List<TimeTable> getAllTimeTables();

    List<TimeTable> getTimeTablesByBatchId(String batchId);

    Boolean updateTimeTable(UpdateTimeTableReq req);

    TimeTable getTimeTableByTimeTableId(String timeTableId);

    List<TimeTable> getTimeTablesByStudentId(String studentId);

    TimeTable getTimeTableByBatchId(String batchId);
}
