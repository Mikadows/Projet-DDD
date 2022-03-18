package use_case;

import infra.CreateEventRequestDTO;
import lombok.RequiredArgsConstructor;
import model.*;
import use_case.exception.AnyAnimatorFoundException;
import use_case.exception.AnySpaceFoundException;

@RequiredArgsConstructor
public class CreateEvent {
    private final Animators animators;
    private final Spaces spaces;
    private final Events events;


    public Event create(CreateEventRequestDTO createEventRequestDTO) {
        Animator animator = animators.findById(createEventRequestDTO.getAnimatorId())
                .orElseThrow(AnyAnimatorFoundException::new);
        Space space = spaces.findById(createEventRequestDTO.getSpaceId())
                .orElseThrow(AnySpaceFoundException::new);

        Event event = new Event(animator, space, createEventRequestDTO);

        animators.book(event.getAnimator(), event.getSchedule());
        spaces.book(space, event.getSchedule());
        events.save(event);

        return event;
    }
}
