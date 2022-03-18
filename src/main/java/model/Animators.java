package model;

import java.util.Optional;
import java.util.UUID;

public interface Animators {

    Optional<Animator> findById(UUID id);
    void bookAvailability(Animator animator, Schedule schedule);
}
