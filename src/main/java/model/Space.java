package model;

import use_case.exception.SpaceNotAvailableException;

import java.util.Set;

public class Space {
    private final SpaceID id;
    private final Integer capacity;
    private Set<Schedule> reservations;

    public Space(SpaceID id, Integer capacity, Set<Schedule> reservations) {
        this.id = id;
        this.capacity = capacity;
        this.reservations = reservations;
    }

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

    public SpaceID getId() {
        return id;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public Set<Schedule> getReservations() {
        return reservations;
    }
}
