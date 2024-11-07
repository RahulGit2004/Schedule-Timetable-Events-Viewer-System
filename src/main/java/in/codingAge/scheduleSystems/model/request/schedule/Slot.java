package in.codingAge.scheduleSystems.model.request.schedule;

import lombok.Getter;
import lombok.Setter;

import java.sql.Time;

@Getter
@Setter
public class Slot {
    private String topic;
    private String instructor;
    private Time startTime;
    private Time endTime;

}
