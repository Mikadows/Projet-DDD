package use_case;

import model.Animator;
import model.Animators;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

public class FakeAnimators implements Animators {
    private HashMap<UUID, Animator> animators = new HashMap<>();

    public FakeAnimators() {
        for (int i = 0; i < 4; i++) {
            UUID id = UUID.randomUUID();
            animators.put(id, new Animator(id, new HashSet<>()));
        }
    }

    @Override
    public Animator findById(UUID id) {
        return animators.get(id);
    }


}
