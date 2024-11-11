package in.codingAge.scheduleSystems.service;

import in.codingAge.scheduleSystems.model.DailySchedule;
import in.codingAge.scheduleSystems.model.request.DailyScheduleRequest;

import java.time.LocalDateTime;
import java.util.List;

public interface DailyScheduleService {
    Boolean createDailySchedule(DailyScheduleRequest dailyScheduleRequest);

    List<DailySchedule> getSchedulesByBatchIdAndDate(String batchId, LocalDateTime date);

    List<DailySchedule> getSchedulesByBatchId(String batchId);
}
