package model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class Animator {
    private final UUID animatorId;
    private Set<ScheduleRange> bookedScheduleRanges;

    public boolean isAvailable(ScheduleRange range) {
        if(range.isPast()) return false;

        boolean isAvailable = true;

        for (ScheduleRange reservation : bookedScheduleRanges) {
            if (reservation.isOverlapping(range)) {
                isAvailable = false;
                break;
            }
        }

        return isAvailable;
    }

    public void book(ScheduleRange scheduleRange) {
        bookedScheduleRanges.add(scheduleRange);
    }
}
