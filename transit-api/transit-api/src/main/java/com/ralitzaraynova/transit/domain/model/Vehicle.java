package com.ralitzaraynova.transit.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.ralitzaraynova.transit.domain.exception.DomainException;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.ArrayList;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne
    private Owner owner;

    private String make;
    private String model;
    private String licenseNumber;

    //@JsonProperty(access = Access.READ_ONLY)
    @Enumerated(EnumType.STRING)
    private VehicleState state;

    private OffsetDateTime registerDate;
    private OffsetDateTime captureDate;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private List<Action> actions = new ArrayList<>();

    public Action addAction(Action action){
        action.setIncidentDate(OffsetDateTime.now());
        action.setVehicle(this);
        getActions().add(action);
        return action;
    };

    public void capture(){
        if(isCaptured()){
            throw new DomainException("The vehicle is already captured");
        }
        setState(VehicleState.CAPTURED);
        setCaptureDate(OffsetDateTime.now());
    }

    public void captureRemove(){
        if(notCaptured()){
            throw  new DomainException("The vehicle is not captured");
        }
        setState(VehicleState.REGULAR);
        setCaptureDate(null);
    }

    public boolean isCaptured(){
        return VehicleState.CAPTURED.equals(getState());
    }

    public boolean notCaptured(){
        return !isCaptured();
    }

}
