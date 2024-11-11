package in.codingAge.scheduleSystems.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "timetables")
@Getter
@Setter
@AllArgsConstructor
public class TimeTable {
    @Id
    private String timeTableId;

    private String creatorId;
    private String batchId;

    private Date date;
    private List<ScheduleEntry> scheduleEntries;
    private Boolean notificationEnabled;

    public TimeTable() {
        this.scheduleEntries = new ArrayList<>();
    }

}