package model.space;

import model.schedule.Schedule;
import use_case.exception.OverlappingScheduleException;
import use_case.exception.SpaceNotAvailableException;

import java.util.Objects;
import java.util.Set;

public class Space {
    private final SpaceID id;
    private final Set<Schedule> reservations;

    public Space(SpaceID id, Set<Schedule> reservations) {
        this.id = id;
        this.reservations = reservations;
    }

    public void book(Schedule schedule){
        try {
            schedule.checkAvailability(reservations);
            reservations.add(schedule);
        }
        catch (OverlappingScheduleException exception){
            throw new SpaceNotAvailableException();
        }
    }

    public SpaceID getId() {
        return id;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Space space = (Space) o;
        return id.equals(space.id) && Objects.equals(reservations, space.reservations);
    }

    public int hashCode() {
        return Objects.hash(id, reservations);
    }
}
