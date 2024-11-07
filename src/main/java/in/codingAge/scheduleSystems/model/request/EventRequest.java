package in.codingAge.scheduleSystems.model.request;

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
    private String batchId;
    private String creatorId;
    private String eventName;
    private String eventStatus;
    private String location;
    private String eventOrganizer;
    private String eventDescription;
    private LocalDateTime eventDate;
    private LocalDateTime eventStartTime;
    private LocalDateTime eventEndTime;
}
