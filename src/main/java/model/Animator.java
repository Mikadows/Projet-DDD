package model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class Animator {
    private final UUID animatorId;
    private List<LocalDateTime> availabilities;
}
