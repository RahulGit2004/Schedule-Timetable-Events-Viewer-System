package in.codingAge.scheduleSystems.service;

import in.codingAge.scheduleSystems.model.ScheduleEntry;
import in.codingAge.scheduleSystems.model.request.ScheduleEntryReq;

import java.util.List;

public interface ScheduleEntryService {
    ScheduleEntry createScheduleEntry(ScheduleEntryReq scheduleEntryReq);

    ScheduleEntry getScheduleEntryById(String scheduleEntryId);

    Boolean updateScheduleEntry(ScheduleEntry scheduleEntry);

    List<ScheduleEntry> getAll();

    ScheduleEntry saveUpdates(ScheduleEntry scheduleEntry);
}
