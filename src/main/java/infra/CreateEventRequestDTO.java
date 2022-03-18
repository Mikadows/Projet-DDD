package infra;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class CreateEventRequestDTO {
    private UUID animatorId;
    private String title;
    private LocalDateTime startDateTime;
    private Duration duration;
    private UUID spaceId;
}
