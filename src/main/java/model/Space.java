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
    private Set<Schedule> reservations;
//    private Location location; maybe ??

    public boolean isAvailable(Schedule range) {
        if(range.isPast()) return false;

        boolean isAvailable = true;

        for (Schedule reservation : reservations) {
            if (reservation.isOverlapping(range)) {
                isAvailable = false;
                break;
            }
        }

        return isAvailable;
    }

    public void book(Schedule scheduleRange) {
        reservations.add(scheduleRange);
    }
}
