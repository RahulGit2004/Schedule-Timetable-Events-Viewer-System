package in.codingAge.scheduleSystems.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Time;
import java.util.Date;

@Document(collection = "schedules")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {

    @Id
    private String scheduleId;

    private String batchId;
    private String batchName;
    private String creatorName;
    private String eventType;
    private Date date;
    private Time time;
}
