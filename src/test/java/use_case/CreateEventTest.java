package use_case;

import model.Animators;
import model.Spaces;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CreateEventTest {
    private Animators animators;
    private Spaces spaces;

    @BeforeEach
    public void init() {
        animators = new FakeAnimators();
        spaces = new FakeSpaces();
    }

    @Test
    public void create_event_with_valid_data() {

    }
}
