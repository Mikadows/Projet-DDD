package model.event;

import model.schedule.Schedule;
import model.animator.Animator;
import model.space.Space;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Event {
    private final EventID id;
    private final Animator animator;
    private final Schedule schedule;
    private StatusEvent status;
    private final Space space;


    public Event(Animator animator, Space space, LocalDateTime startDateTime, Duration duration) {
        Schedule eventSchedule = new Schedule(startDateTime, duration);

        animator.book(eventSchedule);
        space.book(eventSchedule);

        this.id = new EventID(UUID.randomUUID());
        this.status = StatusEvent.CREATED;
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

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(id, event.id);
    }

    public int hashCode() {
        return Objects.hash(id, animator, schedule, status, space);
    }
}
