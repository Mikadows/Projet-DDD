package model;

import infra.CreateEventRequestDTO;

import java.util.UUID;

public class Event {
    private EventID id;
    private Animator animator;
    private String title;
    private Schedule schedule;
    private Space space;


    public Event(Animator animator, Space space, CreateEventRequestDTO eventRequestDTO) {
        Schedule eventSchedule = new Schedule(eventRequestDTO.getStartDateTime(), eventRequestDTO.getDuration());

        animator.book(eventSchedule);
        space.book(eventSchedule);

        this.id = new EventID(UUID.randomUUID());
        this.animator = animator;
        this.title = eventRequestDTO.getTitle();
        this.schedule = eventSchedule;
        this.space = space;
    }

    public EventID getId() {
        return id;
    }

    public Animator getAnimator() {
        return animator;
    }

    public String getTitle() {
        return title;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public Space getSpace() {
        return space;
    }
}
