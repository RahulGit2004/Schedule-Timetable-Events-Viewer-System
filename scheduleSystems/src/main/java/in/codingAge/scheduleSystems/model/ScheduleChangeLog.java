package in.codingAge.scheduleSystems.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "schedule_change")
public class ScheduleChangeLog {
    @Id
    private String scheduleChangeLogId;
    private String timetableId;
    private String scheduleEntryId;
    private String previousDetails;
    private String newDetails;
    private List<String> affectedUsers;
    private Boolean notificationSent;
}
