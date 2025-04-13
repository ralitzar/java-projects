package com.ralitzaraynova.transit.api.representation;

import com.ralitzaraynova.transit.domain.model.VehicleState;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class VehicleRepresentation {

    private Long id;
    private OwnerRepresentationResume owner;
    private String make;
    private String model;
    private String licenseNumber;
    private VehicleState state;
    private OffsetDateTime registerDate;
    private OffsetDateTime captureDate;

}
