package in.codingAge.scheduleSystems.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Time;
import java.util.Date;

@Document(collection = "timetables")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TimeTable {
    @Id
    private String timetableId;
    private String batchId;
    private Date date;
    private Time startTime;
    private Time endTime;
    private String instructor;
    // event type may  test , extra session or events
    private String eventType;
    private String createdAt;
    private String updatedAt;

    // Getters and Setters
}