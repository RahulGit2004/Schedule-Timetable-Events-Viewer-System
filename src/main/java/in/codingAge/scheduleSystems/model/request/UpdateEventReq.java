package in.codingAge.scheduleSystems.model.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UpdateEventReq {

    private String eventId;
    private String creatorId;
    private String batchId;
    private String eventName;
    private String eventOrganizer;
    private String eventDescription;
    private LocalDateTime eventDate;
    private LocalDateTime eventStartTime;
    private LocalDateTime eventEndTime;
}
