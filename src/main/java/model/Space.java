package model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import use_case.exception.SpaceNotAvailableException;

import java.util.Set;
import java.util.UUID;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Space {
    private final SpaceID id;
    private final Integer capacity;
    private Set<Schedule> reservations;
    private Location location;

    public void isAvailable(Schedule range) {
        range.isPast();

        for (Schedule reservation : reservations) {
            if (reservation.isOverlapping(range)) {
                throw new SpaceNotAvailableException();
            }
        }
    }

    public void book(Schedule scheduleRange) {
        isAvailable(scheduleRange);
        reservations.add(scheduleRange);
    }


}
