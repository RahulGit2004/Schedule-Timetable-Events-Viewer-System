package in.codingAge.scheduleSystems.model.response;

import in.codingAge.scheduleSystems.model.TimeTable;
import in.codingAge.scheduleSystems.model.request.schedule.ScheduleTimeTableRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ScheduledTimeTableResponse {
    private TimeTable table;
    private ScheduleTimeTableRequest request;
}
