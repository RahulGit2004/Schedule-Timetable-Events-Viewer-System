package in.codingAge.scheduleSystems.model.request.schedule;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ScheduleTimeTableRequest {
    private String batchId;
    private List<DaySchedule> schedules;

}
