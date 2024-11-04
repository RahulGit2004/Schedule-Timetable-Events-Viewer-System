package in.codingAge.scheduleSystems.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Time;

@Document(collection = "notifications")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Notification {

    @Id
    private String notificationId;
    private String eventId;
    private String batchId;
    private String message;
    private boolean isRead;
    private Time notificationTime;

}
