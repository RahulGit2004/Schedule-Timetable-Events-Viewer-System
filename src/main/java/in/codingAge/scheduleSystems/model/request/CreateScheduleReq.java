package in.codingAge.scheduleSystems.model.request;

import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
public class CreateScheduleReq {

    private String batchId;
    private String creatorId;
    private String className;
    private String instructorName;
    private String location;
    private String eventType; // Replace String with EventType enum
    private String status;
    private LocalDate date;
    private LocalTime time;
    private Duration duration;
}
