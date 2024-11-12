package in.codingAge.scheduleSystems.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;

@Document(collection = "schedule_entries")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleEntry {
    @Id
    private String scheduleEntryId;
    private String timetableId;
    private String className;
    private String instructor;
    private String location;
    private LocalTime startTime;
    private String eventType;
    private Long duration;
}
