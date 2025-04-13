package com.ralitzaraynova.transit.api.controller;

import com.ralitzaraynova.transit.api.assembler.VehicleAssembler;
import com.ralitzaraynova.transit.api.representation.VehicleRepresentation;
import com.ralitzaraynova.transit.api.representation.input.VehicleInput;
import com.ralitzaraynova.transit.domain.exception.DomainException;
import com.ralitzaraynova.transit.domain.model.Owner;
import com.ralitzaraynova.transit.domain.model.Vehicle;
import com.ralitzaraynova.transit.domain.repository.VehicleRepository;
import com.ralitzaraynova.transit.domain.service.ActionRegistrationService;
import com.ralitzaraynova.transit.domain.service.ActionVehicleService;
import com.ralitzaraynova.transit.domain.service.VehicleRegistrationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleRepository vehicleRepository;
    private final VehicleRegistrationService vehicleRegistrationService;
    private final ActionVehicleService actionVehicleService;
    private final VehicleAssembler vehicleAssembler;

    @GetMapping
    public List<VehicleRepresentation> list(){
        return vehicleAssembler.toCollectionModel(vehicleRepository.findAll());
    }

    @GetMapping("/{vehicleId}")
    public ResponseEntity<VehicleRepresentation> search(@PathVariable Long vehicleId) {
        return vehicleRepository.findById(vehicleId)
                .map(vehicleAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VehicleRepresentation register(@Valid @RequestBody VehicleInput vehicleInput){
        Vehicle newVehicle = vehicleAssembler.toEntity(vehicleInput);
        Vehicle registeredVehicle = vehicleRegistrationService.register(newVehicle);
        return vehicleAssembler.toModel(registeredVehicle);
        //return vehicleAssembler.toModel(vehicleRegistrationService.register(vehicle));
    }

    @PutMapping("/{vehicleId}/capture")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void capture(@PathVariable Long vehicleId){
        actionVehicleService.capture(vehicleId);
    }

    @DeleteMapping("/{vehicleId}/capture")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void captureRemove(@PathVariable Long vehicleId){
        actionVehicleService.captureRemove(vehicleId);
    }
}
