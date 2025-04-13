package com.ralitzaraynova.transit.domain.service;

import com.ralitzaraynova.transit.domain.model.Vehicle;
import com.ralitzaraynova.transit.domain.model.VehicleState;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ActionVehicleService {

    private final VehicleRegistrationService vehicleRegistrationService;

    @Transactional
    public void capture(Long vehicleId){
        Vehicle vehicle = vehicleRegistrationService.search(vehicleId);
        vehicle.capture();
    }

    @Transactional
    public void captureRemove(Long vehicleId){
        Vehicle vehicle = vehicleRegistrationService.search(vehicleId);
        vehicle.captureRemove();
    }

}
