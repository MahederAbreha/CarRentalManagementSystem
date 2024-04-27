package edu.miu.cs489.apsd.carrentalsystem.dto.vehicle.response;

import edu.miu.cs489.apsd.carrentalsystem.model.enums.VehicleStatus;
import edu.miu.cs489.apsd.carrentalsystem.model.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleRentalResponse {
    Long vehicleId;
    String plateNumber;
    String make;
    String model;
    Integer year;
    Long mileage;
    String color;
    Double rate;
    VehicleStatus vehicleStatus;
    VehicleType vehicleType;
}