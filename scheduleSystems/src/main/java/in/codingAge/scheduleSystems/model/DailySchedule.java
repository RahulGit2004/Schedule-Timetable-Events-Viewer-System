package in.codingAge.scheduleSystems.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Document(collection = "daily_schedule")
public class DailySchedule {
    @Id
    private String dailyScheduleId;
    private String batchId;
    private String timeTableId;
    private String creatorId;
    private Date date;
    private List<ScheduleEntry> events;

    public DailySchedule(){
        this.events = new ArrayList<>();
    }



}
