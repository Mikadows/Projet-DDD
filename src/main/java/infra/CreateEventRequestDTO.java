package infra;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

public class CreateEventRequestDTO {
    private UUID animatorId;
    private String title;
    private LocalDateTime startDateTime;
    private Duration duration;
    private UUID spaceID;
}
