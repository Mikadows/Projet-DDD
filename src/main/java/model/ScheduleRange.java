package model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ScheduleRange {
    private final LocalDateTime start;
    private final Duration duration;


    public LocalDateTime getEnd() {
        return start.plus(duration);
    }

    public boolean isOverlapping(ScheduleRange other) {
        return !(other.getStart().isAfter(getEnd()) || other.getEnd().isBefore(getStart()));
    }

    public boolean isPast() {
        return LocalDateTime.now().isAfter(getEnd());
    }

}
