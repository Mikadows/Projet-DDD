package use_case;

import infra.CreateEventRequestDTO;
import lombok.RequiredArgsConstructor;
import model.*;

import java.util.ArrayList;
import java.util.UUID;

@RequiredArgsConstructor
public class CreateEvent {
    private final Animators animators;
    private final Spaces spaces;
    private final Events events;


    public Event create(CreateEventRequestDTO createEventRequestDTO) {
        Animator animator = animators.findById(createEventRequestDTO.getAnimatorId())
                .orElseThrow(AnyAnimatorFoundException::new);
        Space space = spaces.findById(createEventRequestDTO.getSpaceID())
                .orElseThrow(AnySpaceFoundException::new);

        Event event = getEvent(animator, space, createEventRequestDTO);

        animators.bookAvailability(event.getAnimator(), event.getSchedule());
        spaces.bookAvailability(event.getSpace(), event.getSchedule());
        events.save(event);

        return event;
    }

    private Event getEvent(Animator animator, Space space, CreateEventRequestDTO eventRequestDTO) {
        Schedule eventSchedule = new Schedule(eventRequestDTO.getStartDateTime(), eventRequestDTO.getDuration());

        if (eventSchedule.isPast()) {
            throw new EventDateIsPastException();
        }

        if (!animator.isAvailable(eventSchedule)) {
            throw new AnimatorNotAvailableException();
        }

        if (!space.isAvailable(eventSchedule)) {
            throw new SpaceNotAvailableException();
        }

        return Event.builder()
                .id(new EventID(UUID.randomUUID()))
                .animator(animator)
                .isPublished(false)
                .space(space)
                .schedule(eventSchedule)
                .title(eventRequestDTO.getTitle())
                .participants(new ArrayList<>())
                .build();
    }
}
