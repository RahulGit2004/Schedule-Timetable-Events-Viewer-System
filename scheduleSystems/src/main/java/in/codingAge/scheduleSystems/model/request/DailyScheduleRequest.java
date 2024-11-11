package in.codingAge.scheduleSystems.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class DailyScheduleRequest {
    private String batchId;
    private String creatorId;
    private Date date;
    private List<ScheduleEntryReq> events;
}
