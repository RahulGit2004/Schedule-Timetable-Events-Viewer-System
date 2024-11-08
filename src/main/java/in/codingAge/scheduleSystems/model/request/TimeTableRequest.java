package in.codingAge.scheduleSystems.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class TimeTableRequest {
    private String batchId;
    private String creatorId;
    private String eventType;
    private String instructor;
    private Time startTime;
    private Time endTime;
    private Date createdAt;
}
