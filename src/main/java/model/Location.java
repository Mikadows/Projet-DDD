package model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@EqualsAndHashCode
@ToString
public class Location {
    private final String type;
    private final String street;
    private final Integer number;
    private final Integer zipCode;
    private final String floor;
    private final Integer door;
    private final String code;
}
