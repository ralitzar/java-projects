package com.ralitzaraynova.transit.domain.repository;

import com.ralitzaraynova.transit.domain.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    public Optional<Vehicle> findByLicenseNumber(String licenseNumber);
}
