package model.animator;

import model.Schedule;
import use_case.exception.AnimatorNotAvailableException;

import java.util.Set;

public class Animator {
    private final AnimatorID id;
    private final Set<Schedule> busySchedules;

    public Animator(AnimatorID id, Set<Schedule> busySchedules) {
        this.id = id;
        this.busySchedules = busySchedules;
    }

    public void isAvailable(Schedule range) {
        range.checkDateAnterior();

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

    public AnimatorID getId() {
        return id;
    }

    public Set<Schedule> getBusySchedules() {
        return busySchedules;
    }
}
