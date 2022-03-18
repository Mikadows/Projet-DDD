package model.animator;

import model.Schedule;
import use_case.exception.AnimatorNotAvailableException;
import use_case.exception.OverlappingScheduleException;
import use_case.exception.SpaceNotAvailableException;

import java.util.Set;

public class Animator {
    private final AnimatorID id;
    private final Set<Schedule> busySchedules;

    public Animator(AnimatorID id, Set<Schedule> busySchedules) {
        this.id = id;
        this.busySchedules = busySchedules;
    }

    public void book(Schedule schedule){
        try {
            schedule.checkAvailability(busySchedules);
            busySchedules.add(schedule);
        }
        catch (OverlappingScheduleException exception){
            throw new AnimatorNotAvailableException();
        }
    }

    public AnimatorID getId() {
        return id;
    }

    public Set<Schedule> getBusySchedules() {
        return busySchedules;
    }
}
