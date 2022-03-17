package model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Event {
    private EventID id;
    private Animator animator;
    private Space space;
    private String title;
    private LocalDateTime start;
    private boolean isPublished;
    private List<UUID> participants;

}
