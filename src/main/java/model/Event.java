package model;

import infra.CreateEventRequestDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode
@ToString
@Getter
public class Event {
    private EventID id;
    private Animator animator;
    private String title;
    private Schedule schedule;
    private boolean isPublished;
    private Space space;
    private List<UUID> participants;


    public Event(Animator animator, Space space, CreateEventRequestDTO eventRequestDTO) {
        Schedule eventSchedule = new Schedule(eventRequestDTO.getStartDateTime(), eventRequestDTO.getDuration());

        animator.book(eventSchedule);
        space.book(eventSchedule);

        this.id = new EventID(UUID.randomUUID());
        this.animator = animator;
        this.title = eventRequestDTO.getTitle();
        this.schedule = eventSchedule;
        this.isPublished = false;
        this.space = space;
        this.participants = new ArrayList<>();
    }
}
