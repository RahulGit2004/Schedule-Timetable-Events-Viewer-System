package in.codingAge.scheduleSystems.model;

import jdk.jfr.EventType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
//todo not in use
@Document(collection = "schedules")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {
    @Id
    private String scheduleId;

    private String batchId;
    private String creatorId;
    private String className;
    private String instructorName;
    private String location;
    private String eventType; // Replace String with EventType enum
    private String status;
    private LocalDate date;
    private LocalTime time;
    private Duration duration;



}
