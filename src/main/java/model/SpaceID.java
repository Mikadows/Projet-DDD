package model;

import java.util.UUID;

public class SpaceID {
    private final UUID spaceID;

    public SpaceID(UUID spaceID) {
        this.spaceID = spaceID;
    }

    public UUID getSpaceID() {
        return spaceID;
    }
}

