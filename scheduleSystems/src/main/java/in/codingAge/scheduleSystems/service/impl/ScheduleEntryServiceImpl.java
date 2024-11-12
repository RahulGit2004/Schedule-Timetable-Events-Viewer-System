package in.codingAge.scheduleSystems.service.impl;

import in.codingAge.scheduleSystems.exception.AppException;
import in.codingAge.scheduleSystems.model.ScheduleEntry;
import in.codingAge.scheduleSystems.model.TimeTable;
import in.codingAge.scheduleSystems.model.request.ScheduleEntryReq;
import in.codingAge.scheduleSystems.repository.ScheduleEntryRepository;
import in.codingAge.scheduleSystems.service.BatchService;
import in.codingAge.scheduleSystems.service.ScheduleEntryService;
import in.codingAge.scheduleSystems.service.TimeTableService;
import in.codingAge.scheduleSystems.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;

@Service
public class ScheduleEntryServiceImpl implements ScheduleEntryService {

    @Autowired
    private ScheduleEntryRepository scheduleEntryRepository;
    @Autowired
    private TimeTableService timeTableService;

    public ScheduleEntry createScheduleEntry(ScheduleEntryReq scheduleEntryReq) {
        // Validate if the timetable exists for the given timetableId
        TimeTable timetable = timeTableService.getTimeTableByTimeTableId(scheduleEntryReq.getTimetableId());
        if (timetable == null) {
            throw new AppException("Invalid TimeTable");
        }

        // Validate schedule conflicts
        boolean isConflicting = checkScheduleConflict(scheduleEntryReq);
        if (isConflicting) {
            throw new AppException("Schedule Conflict");
        }
        ScheduleEntry scheduleEntry = getScheduleEntry(scheduleEntryReq);
        return scheduleEntryRepository.save(scheduleEntry);

    }

    @Override
    public ScheduleEntry getScheduleEntryById(String scheduleEntryId) {
        if (scheduleEntryRepository.findByScheduleEntryId(scheduleEntryId) == null) {
            throw new AppException("Invalid id");
        } else {
            return scheduleEntryRepository.findByScheduleEntryId(scheduleEntryId);
        }
    }

    @Override
    public Boolean updateScheduleEntry(ScheduleEntry scheduleEntry) {
        ScheduleEntry entry = getScheduleEntryById(scheduleEntry.getScheduleEntryId());
        if (entry == null) {
            throw new AppException("Invalid Schedule Entry id");
        } else {
            entry.setDuration(scheduleEntry.getDuration());
            entry.setClassName(scheduleEntry.getClassName());
            entry.setEventType(scheduleEntry.getEventType());
            entry.setInstructor(scheduleEntry.getInstructor());
            entry.setLocation(scheduleEntry.getLocation());
            entry.setStartTime(entry.getStartTime());
            entry.setDuration(entry.getDuration());
            scheduleEntryRepository.save(entry);
            return true;
        }

    }

    @Override
    public List<ScheduleEntry> getAll() {
        return scheduleEntryRepository.findAll();
    }

    private static ScheduleEntry getScheduleEntry(ScheduleEntryReq scheduleEntryReq) {
        ScheduleEntry scheduleEntry = new ScheduleEntry();
        scheduleEntry.setTimetableId(scheduleEntryReq.getTimetableId());
        scheduleEntry.setClassName(scheduleEntryReq.getClassName());
        scheduleEntry.setInstructor(scheduleEntryReq.getInstructor());
        scheduleEntry.setLocation(scheduleEntryReq.getLocation());
        scheduleEntry.setStartTime(scheduleEntryReq.getStartTime());
        scheduleEntry.setEventType(scheduleEntryReq.getEventType());
        scheduleEntry.setDuration(scheduleEntryReq.getDuration());
        return scheduleEntry;
    }


    public boolean checkScheduleConflict(ScheduleEntryReq newScheduleEntry) {
        LocalTime newScheduleStartTime = newScheduleEntry.getStartTime();
        long newScheduleDuration = newScheduleEntry.getDuration();

        // Fetch existing entries from the repository
        List<ScheduleEntry> existingEntries = scheduleEntryRepository.findByTimetableIdAndStartTime(
                newScheduleEntry.getTimetableId(), newScheduleStartTime);

        if (existingEntries.isEmpty()) {
            return false;
        }

        for (ScheduleEntry existingEntry : existingEntries) {

            LocalTime existingScheduleStartTime = existingEntry.getStartTime();
            long existingScheduleDuration = existingEntry.getDuration();

            // Check for overlap
            if (isOverlapping(newScheduleStartTime, newScheduleDuration,
                    existingScheduleStartTime, existingScheduleDuration)) {
                return true; // Conflict found
            }
        }
        return false; // No conflicts found
    }

    private boolean isOverlapping(LocalTime newEntryStart, long newEntryDuration,
                                  LocalTime existingEntryStart, long existingEntryDuration) {
        // Calculate end times by adding duration to start times
        LocalTime newEntryEndTime = newEntryStart.plusSeconds(newEntryDuration);
        LocalTime existingEntryEndTime = existingEntryStart.plusSeconds(existingEntryDuration);

        // Check if the new entry overlaps with the existing entry
        return newEntryStart.isBefore(existingEntryEndTime) && newEntryEndTime.isAfter(existingEntryStart);
    }

}
