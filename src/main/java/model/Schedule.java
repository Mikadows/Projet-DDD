package model;

import use_case.exception.EventDateIsPastException;

import java.time.Duration;
import java.time.LocalDateTime;

public class Schedule {
    private final LocalDateTime start;
    private final Duration duration;

    public Schedule(LocalDateTime start, Duration duration) {
        this.start = start;
        this.duration = duration;
    }

    public LocalDateTime getEnd() {
        return start.plus(duration);
    }

    public boolean isOverlapping(Schedule schedule) {
        return !(schedule.getStart().isAfter(getEnd()) || schedule.getEnd().isBefore(getStart()));
    }

    public void isPast() {
        if(LocalDateTime.now().isAfter(getEnd())){
            throw new EventDateIsPastException();
        }
    }

    public LocalDateTime getStart() {
        return start;
    }

    public Duration getDuration() {
        return duration;
    }

}
