package in.codingAge.scheduleSystems.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "batches")
@Getter
@Setter
@AllArgsConstructor
public class Batch {

    @Id
    private String batchId;
    private String creatorId;
    private String batchName;
    private String batchDescription;
    private String creatorName;
    private Date createdDate;
    private boolean activeBatch;

    // List of students & one admin in this batch
    private List<User> users;

    // Specific timetables for this batch
    private List<TimeTable> timeTables;

    public Batch () {
        this.users = new ArrayList<>();
        this.timeTables = new ArrayList<>();
    }

}
