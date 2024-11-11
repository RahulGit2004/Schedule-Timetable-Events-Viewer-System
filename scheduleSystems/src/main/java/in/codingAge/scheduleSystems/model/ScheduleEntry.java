package in.codingAge.scheduleSystems.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

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
    private Time startTime;
    private String eventType;
    private Long duration;
}
