package com.ralitzaraynova.transit.api.representation.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ActionInput {

    @NotBlank
    private String description;

    @NotNull
    @Positive
    private BigDecimal fineValue;
}
