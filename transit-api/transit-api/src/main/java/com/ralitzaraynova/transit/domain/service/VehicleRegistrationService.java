package com.ralitzaraynova.transit.domain.service;

import com.ralitzaraynova.transit.domain.exception.DomainException;
import com.ralitzaraynova.transit.domain.exception.EntityNotFoundException;
import com.ralitzaraynova.transit.domain.model.Owner;
import com.ralitzaraynova.transit.domain.model.Vehicle;
import com.ralitzaraynova.transit.domain.model.VehicleState;
import com.ralitzaraynova.transit.domain.repository.OwnerRepository;
import com.ralitzaraynova.transit.domain.repository.VehicleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
public class VehicleRegistrationService {

    private final VehicleRepository vehicleRepository;
    private final OwnerRegistrationService ownerRegistrationService;

    public Vehicle search(Long vehicleId){
        return vehicleRepository.findById(vehicleId)
                .orElseThrow(()-> new EntityNotFoundException("Vehicle not found"));
    }

    @Transactional
    public Vehicle register(Vehicle newVehicle){
        if(newVehicle.getId() != null){
            throw new DomainException("To register a vehicle, it must not have an identification number!");
        }
        boolean existingLicenseNumber = vehicleRepository
                .findByLicenseNumber(newVehicle.getLicenseNumber())
                .filter(vehicle -> !vehicle.equals(newVehicle))
                .isPresent();

        if(existingLicenseNumber){
            throw new DomainException("There is already a registered vehicle with this license number!");
        }

        Owner owner = ownerRegistrationService.search(newVehicle.getOwner().getId());

        newVehicle.setOwner(owner);
        newVehicle.setState(VehicleState.REGULAR);
        newVehicle.setRegisterDate(OffsetDateTime.now());

        return vehicleRepository.save(newVehicle);
    }
}
