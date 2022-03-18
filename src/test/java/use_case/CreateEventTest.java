package use_case;

import infra.CreateEventRequestDTO;
import model.Animators;
import model.Event;
import model.Events;
import model.Spaces;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class CreateEventTest {
    private Animators animators;
    private Spaces spaces;
    private Events events;
    private CreateEvent employe;

    @BeforeEach
    public void init() {
        animators = new FakeAnimators();
        spaces = new FakeSpaces();
        events = new FakeEvents();
        employe = new CreateEvent(animators, spaces, events);
    }

    @Test
    public void create_event_with_past_date() {
        CreateEventRequestDTO fakeEvent = new CreateEventRequestDTO(
                UUID.fromString("091b9ea5-b4ab-46cf-9e53-dee70eb85c71"),
                "Aymeric Anniversary",
                LocalDateTime.now().minusDays(10),
                Duration.ofDays(2),
                UUID.fromString("091b9ea5-b4ab-46cf-9e53-daa80eb85c71"));

        spaces.findById(UUID.fromString("091b9ea5-b4ab-46cf-9e53-daa80eb85c71"));
        ThrowingCallable createEvent =
                () -> employe.create(fakeEvent);

        assertThatExceptionOfType(EventDateIsPastException.class).isThrownBy(createEvent);

    }

    @Test
    public void create_event_with_space_not_found() {
        CreateEventRequestDTO fakeEvent = new CreateEventRequestDTO(UUID.fromString("091b9ea5-b4ab-46cf-9e53-dee70eb85c71"),
                "Aymeric Anniversary", LocalDateTime.now().plusDays(10),
                Duration.ofDays(2), UUID.fromString("091b9ea5-b4ab-46cf-9e53-daa90eb85c71"));
        ThrowingCallable createEvent =
                () -> employe.create(fakeEvent);

        assertThatExceptionOfType(AnySpaceFoundException.class).isThrownBy(createEvent);

    }
}
