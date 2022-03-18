package model.space;

import model.Schedule;
import use_case.exception.OverlappingScheduleException;
import use_case.exception.SpaceNotAvailableException;

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
}
