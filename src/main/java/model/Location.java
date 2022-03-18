package model;

import lombok.*;

@Builder
@ToString
@RequiredArgsConstructor
public class Location {
    private final String type;
    private final String city;
    private final String street;
    private final Integer number;
    private final Integer zipCode;
    private final String floor;
    private final Integer door;
    private final String code;
}
