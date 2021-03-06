package use_case;

import infra.CreateEventRequestDTO;
import model.animator.Animators;
import model.event.Events;
import model.space.Spaces;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.exception.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class CreateEventTest {
    private Animators animators;
    private Spaces spaces;
    private Events events;
    private CreateEvent organisator;

    @BeforeEach
    public void init() {
        animators = new FakeAnimators();
        spaces = new FakeSpaces();
        events = new FakeEvents();
        organisator = new CreateEvent(animators, spaces, events);
    }

    @Test
    public void create_event_with_past_date() {
        CreateEventRequestDTO fakeEvent = new CreateEventRequestDTO(
                UUID.fromString("091b9ea5-b4ab-46cf-9e53-dee70eb85c71"),
                "Aymeric Anniversary",
                LocalDateTime.now().minusDays(10),
                Duration.ofDays(2),
                UUID.fromString("091b9ea5-b4ab-46cf-9e53-daa80eb85c71"));

        ThrowingCallable createEvent =
                () -> organisator.create(fakeEvent);

        assertThatExceptionOfType(EventDateIsPastException.class).isThrownBy(createEvent);

    }

    @Test
    public void create_event_with_space_not_found() {
        CreateEventRequestDTO fakeEvent = new CreateEventRequestDTO(UUID.fromString("091b9ea5-b4ab-46cf-9e53-dee70eb85c71"),
                "Aymeric Anniversary", LocalDateTime.now().plusDays(10),
                Duration.ofDays(2), UUID.randomUUID());
        ThrowingCallable createEvent =
                () -> organisator.create(fakeEvent);

        assertThatExceptionOfType(AnySpaceFoundException.class).isThrownBy(createEvent);

    }

    @Test
    public void create_event_with_animator_not_available() {
        CreateEventRequestDTO fakeEvent = new CreateEventRequestDTO(UUID.fromString("809f0053-90f5-45f5-a725-09bd13e827c4"),
                "Aymeric Anniversary", LocalDateTime.now().plusDays(5),
                Duration.ofDays(2), UUID.fromString("091b9ea5-b4ab-46cf-9e53-daa80eb85c71"));
        ThrowingCallable createEvent =
                () -> organisator.create(fakeEvent);

        assertThatExceptionOfType(AnimatorNotAvailableException.class).isThrownBy(createEvent);

    }

    @Test
    public void create_event_with_animator_not_found() {
        CreateEventRequestDTO fakeEvent = new CreateEventRequestDTO(UUID.randomUUID(),
                "Aymeric Anniversary", LocalDateTime.now().plusDays(8),
                Duration.ofDays(2), UUID.fromString("091b9ea5-b4ab-46cf-9e53-daa80eb85c71"));
        ThrowingCallable createEvent =
                () -> organisator.create(fakeEvent);

        assertThatExceptionOfType(AnyAnimatorFoundException.class).isThrownBy(createEvent);
    }

    @Test
    public void create_event_with_space_not_available() {
        CreateEventRequestDTO fakeEvent = new CreateEventRequestDTO(UUID.fromString("809f0053-90f5-45f5-a725-09bd13e827c4"),
                "Aymeric Anniversary", LocalDateTime.now().plusDays(10),
                Duration.ofHours(3), UUID.fromString("091b9ea5-b4ab-46cf-9e53-daa80eb85c71"));

        ThrowingCallable createEvent =
                () -> organisator.create(fakeEvent);

        assertThatExceptionOfType(SpaceNotAvailableException.class).isThrownBy(createEvent);
    }

    @Test
    public void create_event_does_not_throw_any_exception() {
        CreateEventRequestDTO fakeEvent = new CreateEventRequestDTO(UUID.fromString("809f0053-90f5-45f5-a725-09bd13e827c4"),
                "Aymeric Anniversary", LocalDateTime.now().plusDays(25),
                Duration.ofHours(1), UUID.fromString("091b9ea5-b4ab-46cf-9e53-daa80eb85c71"));

        Assertions.assertThatCode(() -> organisator.create(fakeEvent)).doesNotThrowAnyException();
    }

    @Test
    public void create_event_animator_not_available() {
        CreateEventRequestDTO fakeEvent = new CreateEventRequestDTO(UUID.fromString("809f0053-90f5-45f5-a725-09bd13e827c4"),
                "Aymeric Anniversary", LocalDateTime.now().plusDays(25),
                Duration.ofHours(1), UUID.fromString("091b9ea5-b4ab-46cf-9e53-daa80eb85c71"));

        CreateEventRequestDTO fakeEvent2 = new CreateEventRequestDTO(UUID.fromString("809f0053-90f5-45f5-a725-09bd13e827c4"),
                "Aymeric Anniversary", LocalDateTime.now().plusDays(25),
                Duration.ofHours(1), UUID.fromString("091b9ea5-b4ab-46cf-9e53-daa70eb85c71"));

        Assertions.assertThatCode(() -> organisator.create(fakeEvent)).doesNotThrowAnyException();
        ThrowingCallable createEvent =
                () -> organisator.create(fakeEvent2);
        assertThatExceptionOfType(AnimatorNotAvailableException.class).isThrownBy(createEvent);
    }

    @Test
    public void create_event_space_booked() {
        CreateEventRequestDTO fakeEvent = new CreateEventRequestDTO(UUID.fromString("809f0053-90f5-45f5-a725-09bd13e827c4"),
                "Aymeric Anniversary", LocalDateTime.now().plusDays(25),
                Duration.ofHours(1), UUID.fromString("091b9ea5-b4ab-46cf-9e53-daa80eb85c71"));

        Assertions.assertThatCode(() -> organisator.create(fakeEvent)).doesNotThrowAnyException();

        CreateEventRequestDTO fakeEvent2 = new CreateEventRequestDTO(UUID.fromString("091b9ea5-b4ab-46cf-9e53-dee70eb85c71"),
                "Aymeric Anniversary", LocalDateTime.now().plusDays(25),
                Duration.ofHours(1), UUID.fromString("091b9ea5-b4ab-46cf-9e53-daa80eb85c71"));

        ThrowingCallable createEvent =
                () -> organisator.create(fakeEvent2);
        assertThatExceptionOfType(SpaceNotAvailableException.class).isThrownBy(createEvent);
    }
}
