package model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

public class Event {
    private final EventID id;
    private final Animator animator;
    private final Schedule schedule;
    private final Space space;


    public Event(Animator animator, Space space, LocalDateTime startDateTime, Duration duration) {
        Schedule eventSchedule = new Schedule(startDateTime, duration);

        animator.book(eventSchedule);
        space.book(eventSchedule);

        this.id = new EventID(UUID.randomUUID());
        this.animator = animator;
        this.schedule = eventSchedule;
        this.space = space;
    }

    public EventID getId() {
        return id;
    }

    public Animator getAnimator() {
        return animator;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public Space getSpace() {
        return space;
    }
}
