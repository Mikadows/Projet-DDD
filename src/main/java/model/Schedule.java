package model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import use_case.exception.EventDateIsPastException;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class Schedule {
    private final LocalDateTime start;
    private final Duration duration;


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

}
