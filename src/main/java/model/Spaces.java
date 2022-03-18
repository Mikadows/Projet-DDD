package model;

import java.util.Optional;
import java.util.UUID;

public interface Spaces {

    Optional<Space> findById(UUID id);
    void book(Space space, Schedule schedule);
}
