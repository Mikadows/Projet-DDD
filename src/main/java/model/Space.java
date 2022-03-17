package model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class Space {
    private final Integer capacity;
    private final String type;
    private List<LocalDateTime> availabilities;
}
