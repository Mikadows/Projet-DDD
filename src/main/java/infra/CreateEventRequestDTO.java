package infra;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

public class CreateEventRequestDTO {
    private UUID animatorId;
    private String title;
    private LocalDateTime startDateTime;
    private Duration duration;
    private UUID spaceId;

    public CreateEventRequestDTO(UUID animatorId, String title,
                                 LocalDateTime startDateTime,
                                 Duration duration, UUID spaceId) {
        this.animatorId = animatorId;
        this.title = title;
        this.startDateTime = startDateTime;
        this.duration = duration;
        this.spaceId = spaceId;
    }

    public UUID getAnimatorId() {
        return animatorId;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public Duration getDuration() {
        return duration;
    }

    public UUID getSpaceId() {
        return spaceId;
    }
}
