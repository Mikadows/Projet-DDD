package use_case;

import model.Animator;
import model.Animators;
import model.ScheduleRange;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class FakeAnimators implements Animators {
    private HashMap<UUID, Animator> animators = new HashMap<>();

    public FakeAnimators() {
        UUID id1 = UUID.fromString("091b9ea5-b4ab-46cf-9e53-dee70eb85c71");
        animators.put(id1, new Animator(id1, new HashSet<>()));

        UUID id2 = UUID.fromString("809f0053-90f5-45f5-a725-09bd13e827c4");
        Set<ScheduleRange> set2 = new HashSet<>();
        set2.add(new ScheduleRange(LocalDateTime.parse("2022-03-17T22:26:02.575492900"), Duration.ofHours(1)));
        animators.put(id2, new Animator(id2, set2));
    }

    @Override
    public Animator findById(UUID id) {
        return animators.get(id);
    }


}
