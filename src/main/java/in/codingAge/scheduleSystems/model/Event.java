package in.codingAge.scheduleSystems.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "events")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    @Id
    private String eventId;
    private String creatorId;
    private String batchId;
    private String eventName;
    private String eventOrganizer;
    private String eventDescription;
    private String location;
    private String status;
    private boolean isActive;
    private LocalDateTime eventDate;
    private LocalDateTime eventStartTime;
    private LocalDateTime eventEndTime;


}
