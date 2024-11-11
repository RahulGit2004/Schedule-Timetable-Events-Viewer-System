package in.codingAge.scheduleSystems.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class EventRequest {
    private String creatorId;
    private String batchId;
    private String title;
    private String organizer;
    private String description;
    private String location;
    private String eventType;
    private Date Date;
    private Time Time;
}
