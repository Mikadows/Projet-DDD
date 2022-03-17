package model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Set;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Space {
    private final Integer capacity;
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
