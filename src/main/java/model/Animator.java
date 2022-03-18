package model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Set;
import java.util.UUID;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Animator {
    private final AnimatorID id;
    private Set<Schedule> busySchedules;

    public boolean isAvailable(Schedule range) {
        if (range.isPast()) return false;

        boolean isAvailable = true;

        for (Schedule booked : busySchedules) {
            if (booked.isOverlapping(range)) {
                isAvailable = false;
                break;
            }
        }

        return isAvailable;
    }

    public void book(Schedule scheduleRange) {
        busySchedules.add(scheduleRange);
    }
}
