package in.codingAge.scheduleSystems.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Time;
import java.util.Date;

@Document(collection = "events")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    @Id
    private String eventId;
    private String batchId;
    private String eventName;
    private String eventOrganizer;
    private Date eventDate;
    private Time eventStartTime;
    private Time eventEndTime;
    private String eventDescription;

}
