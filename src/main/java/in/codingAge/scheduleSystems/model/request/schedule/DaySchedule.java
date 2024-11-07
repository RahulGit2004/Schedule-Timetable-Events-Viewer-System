package in.codingAge.scheduleSystems.model.request.schedule;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DaySchedule {
    private String day;
    private List<Slot> slots;

}
