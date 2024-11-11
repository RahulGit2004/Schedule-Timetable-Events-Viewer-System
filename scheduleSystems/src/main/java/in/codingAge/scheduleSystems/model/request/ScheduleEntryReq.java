package in.codingAge.scheduleSystems.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;

@Getter
@Setter
@NoArgsConstructor

public class ScheduleEntryReq {
    private String timetableId;
    private String className;
    private String instructor;
    private String location;
    private Time startTime;
    private String eventType;
    private Long duration;
}
