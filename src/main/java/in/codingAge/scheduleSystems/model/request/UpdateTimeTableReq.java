package in.codingAge.scheduleSystems.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class UpdateTimeTableReq {
    private String batchId;
    private String eventType;
    private String instructor;
    private Date updatedAt;
    private Time startTime;
    private Time endTime;
}
