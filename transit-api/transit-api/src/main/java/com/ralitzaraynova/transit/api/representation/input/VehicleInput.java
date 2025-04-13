package com.ralitzaraynova.transit.api.representation.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleInput {

    @NotBlank
    private String make;

    @NotBlank
    @Size(max = 20)
    private String model;

    @NotBlank
    @Pattern(regexp = "[A-Z]{2}[0-9]{6}[A-Z]{2}")
    private String licenseNumber;

    @NotNull
    @Valid
    private OwnerIdInput owner;


}
