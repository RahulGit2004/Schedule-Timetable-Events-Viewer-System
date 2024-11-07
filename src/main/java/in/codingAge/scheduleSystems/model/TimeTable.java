package in.codingAge.scheduleSystems.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
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

    private String creatorId;
    private String batchId;
    private String eventType; // event type may  test , extra session or events
    private String instructor;
    private Date date;
    private Time startTime;
    private Time endTime;

    @CreatedDate
    private Date createdAt; // when timeTable created , it will never be modifiable
    @LastModifiedDate
    private Date updatedAt; // it helps when admin wants to update the time of particular batch
}