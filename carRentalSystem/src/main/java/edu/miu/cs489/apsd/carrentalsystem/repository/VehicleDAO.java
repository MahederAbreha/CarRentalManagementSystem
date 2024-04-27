package edu.miu.cs489.apsd.carrentalsystem.repository;

import edu.miu.cs489.apsd.carrentalsystem.model.enums.VehicleStatus;
import edu.miu.cs489.apsd.carrentalsystem.model.vehicle.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleDAO extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findAllByVehicleStatus(VehicleStatus vehicleStatus);
}
