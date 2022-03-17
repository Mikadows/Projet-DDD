package use_case;

import model.ScheduleRange;
import model.Space;
import model.Spaces;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class FakeSpaces implements Spaces {
    private Set<Space> spaces;

    public FakeSpaces() {
        spaces = new HashSet<>();
        spaces.add(new Space(5, new HashSet<>()));
        Set<ScheduleRange> scheduleRanges = new HashSet<>();
        scheduleRanges.add(new ScheduleRange(LocalDateTime.parse("2022-03-17T22:26:02.575492900"), Duration.ofHours(1)));
        scheduleRanges.add(new ScheduleRange(LocalDateTime.parse("2022-03-17T19:26:02.575492900"), Duration.ofHours(1)));
        scheduleRanges.add(new ScheduleRange(LocalDateTime.parse("2022-03-17T18:00:00.575492900"), Duration.ofHours(1)));
        spaces.add(new Space(6, scheduleRanges));
    }

    public void display() {
        System.out.println(spaces);
    }

    @Override
    public Space findById(UUID id) {
        return null;
    }
}
