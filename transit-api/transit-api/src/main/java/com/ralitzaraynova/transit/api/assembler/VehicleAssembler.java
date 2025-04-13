package com.ralitzaraynova.transit.api.assembler;

import com.ralitzaraynova.transit.api.representation.VehicleRepresentation;
import com.ralitzaraynova.transit.api.representation.input.VehicleInput;
import com.ralitzaraynova.transit.domain.model.Vehicle;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.Comment;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class VehicleAssembler {

    private final ModelMapper modelMapper;

    public Vehicle toEntity(VehicleInput vehicleInput){
        return modelMapper.map(vehicleInput, Vehicle.class);
    }

    public VehicleRepresentation toModel(Vehicle vehicle){
        return modelMapper.map(vehicle, VehicleRepresentation.class);
    }

    public List<VehicleRepresentation> toCollectionModel(List<Vehicle> vehicles){
        return vehicles.stream().map(this::toModel).toList();
    }
}
