package use_case;

import infra.CreateEventRequestDTO;
import model.animator.Animator;
import model.animator.Animators;
import model.event.Event;
import model.event.Events;
import model.space.Space;
import model.space.Spaces;
import use_case.exception.AnyAnimatorFoundException;
import use_case.exception.AnySpaceFoundException;

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
        Animator animator = animators.findById(createEventRequestDTO.getAnimatorId())
                .orElseThrow(AnyAnimatorFoundException::new);
        Space space = spaces.findById(createEventRequestDTO.getSpaceId())
                .orElseThrow(AnySpaceFoundException::new);

        Event event = new Event(animator, space,
                createEventRequestDTO.getStartDateTime(), createEventRequestDTO.getDuration());

        animators.book(event.getAnimator(), event.getSchedule());
        spaces.book(space, event.getSchedule());
        events.save(event);

        return event;
    }
}
