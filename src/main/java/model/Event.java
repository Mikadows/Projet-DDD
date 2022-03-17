package model;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
public class Event {
    private EventID id;
    private Animator animator;
    private Space space;
    private String title;
    private boolean isPublished;
    private List<UUID> participants;

}