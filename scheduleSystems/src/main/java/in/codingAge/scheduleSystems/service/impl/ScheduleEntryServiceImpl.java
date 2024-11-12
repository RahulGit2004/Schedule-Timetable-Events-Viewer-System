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

    @Override
    public ScheduleEntry saveUpdates(ScheduleEntry scheduleEntry) {
        return scheduleEntryRepository.save(scheduleEntry);
    }

    private static ScheduleEntry getScheduleEntry(ScheduleEntryReq scheduleEntryReq) {
        ScheduleEntry scheduleEntry = new ScheduleEntry();
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
        List<ScheduleEntry> allEntries = scheduleEntryRepository.findAll();

        if (allEntries.isEmpty()) {
            return false;
        }

        for (ScheduleEntry entry : allEntries) {

            LocalTime existStartTime = entry.getStartTime();
            long existDuration = entry.getDuration();

            // Check for overlap
            if (isOverlapping(newScheduleStartTime, newScheduleDuration,
                    existStartTime, existDuration)) {
                return true; // Conflict
            }
        }
        return false; // No conflicts
    }

    private boolean isOverlapping(LocalTime newStartTime, long newDuration,
                                  LocalTime existStartTime, long existDuration) {
        // Calculate end times by adding duration to start times
        LocalTime newEntryEndTime = newStartTime.plusSeconds(newDuration); // startTime + duration = endTime
        LocalTime existingEntryEndTime = existStartTime.plusSeconds(existDuration);

        // Check if the new entry overlaps with the existing entry
        return newStartTime.isBefore(existingEntryEndTime) && newEntryEndTime.isAfter(existStartTime);
    }

}
