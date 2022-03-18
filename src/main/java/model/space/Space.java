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

    public void isAvailable(Schedule schedule) {
        schedule.checkDateAnterior();

        for (Schedule reservation : reservations) {
            if (reservation.isOverlapping(schedule)) {
                throw new SpaceNotAvailableException();
            }
        }
    }

    public void book(Schedule schedule) {
        isAvailable(schedule);
        reservations.add(schedule);
    }

    public SpaceID getId() {
        return id;
    }
}
