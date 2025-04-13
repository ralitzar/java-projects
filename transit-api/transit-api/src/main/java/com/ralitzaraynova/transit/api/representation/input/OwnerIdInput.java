package com.ralitzaraynova.transit.api.representation.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OwnerIdInput {

    @NotNull
    private Long id;
}
