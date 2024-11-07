package in.codingAge.scheduleSystems.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "batches")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Batch {

    @Id
    private String batchId;
    private String creatorId;
    private String batchName;
    private String creatorName;
    private Date createdDate;
    private boolean isActive;

    // List of students & one admin in this batch
    private List<User> users;

    // List of events specific to this batch
    private List<Event> events;

    // List of schedules specific to this batch
    private List<Schedule> schedules;

    // Specific timetables for this batch
    private List<TimeTable> timeTables;

}
