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
    private String batchName;
    private String creatorName;
    private Date createdDate;
    private boolean isActive;

    // storing list of student in this batch
    private List<User> studentList;
    // for getting batch specific events
    private List<Schedule> schedules;
    // for each batch have specific time and table
    private List<TimeTable> timeTables;

}
