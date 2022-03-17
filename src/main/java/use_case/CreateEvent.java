package use_case;

import infra.CreateEventRequestDTO;
import model.*;

import java.util.ArrayList;
import java.util.UUID;

public class CreateEvent {
    private final Animators animators;
    private final Spaces spaces;
    private final Events events;


    public CreateEvent(Animators animators, Spaces spaces, Events events) {
        this.animators = animators;
        this.spaces = spaces;
        this.events = events;
    }

    public Event create(CreateEventRequestDTO createEventRequestDTO) {
        // Get Animator from id
        Animator animator = animators.findById(createEventRequestDTO.getAnimatorId());
        // Get Space from id
        Space space = spaces.findById(createEventRequestDTO.getSpaceID());

        // Create ScheduleRange
        ScheduleRange scheduleRange = new ScheduleRange(
                createEventRequestDTO.getStartDateTime(), createEventRequestDTO.getDuration());

        // Is the animator available ?
        if (animator.isAvailable(scheduleRange)) {
            // Book the animator
            animator.book(scheduleRange);
        }
        // Is the space available ?
        if (space.isAvailable(scheduleRange)) {
            // Book the space
            space.book(scheduleRange);
        }

        // Create event entity
        Event event = Event.builder()
                .id(new EventID(UUID.randomUUID()))
                .animator(animator)
                .isPublished(false)
                .space(space)
                .title(createEventRequestDTO.getTitle())
                .participants(new ArrayList<>())
                .build();

        // save event
        events.save(event);
        return event;
    }
}
