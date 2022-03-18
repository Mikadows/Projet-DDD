package model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import use_case.exception.AnimatorNotAvailableException;

import java.util.Set;
import java.util.UUID;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Animator {
    private final AnimatorID id;
    private Set<Schedule> busySchedules;

    public void isAvailable(Schedule range) {
        range.isPast();

        for (Schedule booked : busySchedules) {
            if (booked.isOverlapping(range)) {
                throw new AnimatorNotAvailableException();
            }
        }
    }

    public void book(Schedule scheduleRange) {
        isAvailable(scheduleRange);
        busySchedules.add(scheduleRange);
    }
}
