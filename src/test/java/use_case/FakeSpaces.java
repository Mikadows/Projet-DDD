package use_case;

import model.schedule.Schedule;
import model.space.Space;
import model.space.SpaceID;
import model.space.Spaces;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class FakeSpaces implements Spaces {
    private final Set<Space> spaces;

    public FakeSpaces() {
        spaces = new HashSet<>();
        spaces.add(new Space(new SpaceID(UUID.fromString("091b9ea5-b4ab-46cf-9e53-daa70eb85c71")),
                new HashSet<>()));
        Set<Schedule> scheduleRanges = new HashSet<>();
        scheduleRanges.add(new Schedule(LocalDateTime.now().plusDays(10), Duration.ofHours(1)));
        scheduleRanges.add(new Schedule(LocalDateTime.now().plusDays(15), Duration.ofHours(1)));
        scheduleRanges.add(new Schedule(LocalDateTime.now().plusDays(20), Duration.ofHours(1)));
        spaces.add(new Space(new SpaceID(UUID.fromString("091b9ea5-b4ab-46cf-9e53-daa80eb85c71")), scheduleRanges));
    }

    public Optional<Space> findById(UUID id) {
        return spaces.stream().filter(space -> space.getId().getSpaceID().equals(id)).findFirst();
    }

    public void book(Space space, Schedule Schedule) {

    }
}
