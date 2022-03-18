package model.space;

import model.Schedule;
import use_case.exception.SpaceNotAvailableException;

import java.util.Set;

public class Space {
    private final SpaceID id;
    private final Set<Schedule> reservations;

    public Space(SpaceID id, Set<Schedule> reservations) {
        this.id = id;
        this.reservations = reservations;
    }

    public void isAvailable(Schedule range) {
        range.checkDateAnterior();

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

    public SpaceID getId() {
        return id;
    }
}
