package com.ralitzaraynova.transit.api.controller;

import com.ralitzaraynova.transit.api.assembler.ActionAssembler;
import com.ralitzaraynova.transit.api.representation.ActionRepresentation;
import com.ralitzaraynova.transit.api.representation.input.ActionInput;
import com.ralitzaraynova.transit.domain.model.Action;
import com.ralitzaraynova.transit.domain.model.Vehicle;
import com.ralitzaraynova.transit.domain.service.ActionRegistrationService;
import com.ralitzaraynova.transit.domain.service.VehicleRegistrationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/vehicles/{vehicleId}/actions")
public class ActionController {

    private final ActionAssembler actionAssembler;
    private final ActionRegistrationService actionRegistrationService;
    private final VehicleRegistrationService vehicleRegistrationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ActionRepresentation register(@PathVariable Long vehicleId,
                                         @Valid @RequestBody ActionInput actionInput){

        Action newAction = actionAssembler.toEntity(actionInput);
        Action registeredAction = actionRegistrationService.register(vehicleId, newAction);
        return actionAssembler.toModel(registeredAction);
    }

    @GetMapping
    public List<ActionRepresentation> list(@PathVariable Long vehicleId){
        Vehicle vehicle = vehicleRegistrationService.search(vehicleId);
        return actionAssembler.toCollectionModel(vehicle.getActions());

    }


}
