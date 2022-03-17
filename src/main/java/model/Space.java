package model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public class Space {
    private final Integer capacity;
    private final String type;
    private Set<ScheduleRange> reservations;

    public boolean isAvailable(ScheduleRange range) {
        if(range.isPast()) return false;

        boolean isAvailable = true;

        for (ScheduleRange reservation : reservations) {
            if (reservation.isOverlapping(range)) {
                isAvailable = false;
                break;
            }
        }

        return isAvailable;
    }

    public void book(ScheduleRange scheduleRange) {
        reservations.add(scheduleRange);
    }
}
