package use_case;

import model.Schedule;
import model.Space;
import model.Spaces;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class FakeSpaces implements Spaces {
    private final Set<Space> spaces;

    public FakeSpaces() {
        spaces = new HashSet<>();
        spaces.add(new Space(5, new HashSet<>()));
        Set<Schedule> scheduleRanges = new HashSet<>();
        scheduleRanges.add(new Schedule(LocalDateTime.parse("2022-03-17T22:26:02.575492900"), Duration.ofHours(1)));
        scheduleRanges.add(new Schedule(LocalDateTime.parse("2022-03-17T19:26:02.575492900"), Duration.ofHours(1)));
        scheduleRanges.add(new Schedule(LocalDateTime.parse("2022-03-17T18:00:00.575492900"), Duration.ofHours(1)));
        spaces.add(new Space(6, scheduleRanges));
    }

    public Optional<Space> findById(UUID id) {
        return Optional.empty();
    }

    public void bookAvailability(Space space, Schedule Schedule) {

    }
}
