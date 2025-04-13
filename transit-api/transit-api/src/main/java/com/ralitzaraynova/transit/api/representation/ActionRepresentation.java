package com.ralitzaraynova.transit.api.representation;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class ActionRepresentation {

    private Long id;
    private String description;
    private BigDecimal fineValue;
    private OffsetDateTime incidentDate;
}
