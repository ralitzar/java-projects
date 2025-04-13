package com.ralitzaraynova.transit.domain.service;

import com.ralitzaraynova.transit.domain.model.Action;
import com.ralitzaraynova.transit.domain.model.Vehicle;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class ActionRegistrationService {

    private VehicleRegistrationService vehicleRegistrationService;

    @Transactional
    public Action register(Long vehicleId, Action newAction){
        Vehicle vehicle = vehicleRegistrationService.search(vehicleId);
        return vehicle.addAction(newAction);
    }

}
