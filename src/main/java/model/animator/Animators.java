package model.animator;

import model.schedule.Schedule;

import java.util.Optional;
import java.util.UUID;

public interface Animators {

    Optional<Animator> findById(UUID id);
    void book(Animator animator, Schedule schedule);
}
