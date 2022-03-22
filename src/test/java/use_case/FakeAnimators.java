package use_case;

import model.schedule.Schedule;
import model.animator.Animator;
import model.animator.AnimatorID;
import model.animator.Animators;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class FakeAnimators implements Animators {
    private final Set<Animator> animators;

    public FakeAnimators() {
        animators = new HashSet<>();
        UUID id1 = UUID.fromString("091b9ea5-b4ab-46cf-9e53-dee70eb85c71");
        animators.add(new Animator(new AnimatorID(id1), new HashSet<>()));

        UUID id2 = UUID.fromString("809f0053-90f5-45f5-a725-09bd13e827c4");
        Set<Schedule> schedule = new HashSet<>();
        schedule.add(new Schedule(LocalDateTime.now().plusDays(5), Duration.ofHours(1)));
        animators.add(new Animator(new AnimatorID(id2), schedule));
    }

    public Optional<Animator> findById(UUID id) {
        return animators.stream().filter(animator -> animator.getId().getAnimatorId().equals(id)).findFirst();
    }

    public void book(Animator animator, Schedule schedule) {
        animator.getBusySchedules().add(schedule);
    }
}
