package model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Builder
@EqualsAndHashCode
@ToString
@Getter
public class Event {
    private EventID id;
    private Animator animator;
    private String title;
    private Schedule schedule;
    private boolean isPublished;
    private Space space;
    private List<UUID> participants;

}
