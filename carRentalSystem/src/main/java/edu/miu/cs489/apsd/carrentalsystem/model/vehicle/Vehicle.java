package edu.miu.cs489.apsd.carrentalsystem.model.vehicle;

import edu.miu.cs489.apsd.carrentalsystem.model.enums.VehicleStatus;
import edu.miu.cs489.apsd.carrentalsystem.model.enums.VehicleType;
import edu.miu.cs489.apsd.carrentalsystem.model.vehicle.Rental;
import edu.miu.cs489.apsd.carrentalsystem.model.vehicle.Reservation;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehicleId;
    @NotBlank(message = "Plate number is required")
    private String plateNumber;
    @NotBlank(message = "Make is required")
    private String make;
    @NotBlank(message = "Model is required")
    private String model;
    @NotNull(message = "Year is required")
    private Integer year;
    @NotNull(message = "Mileage is required")
    private Long mileage;
    @NotBlank(message = "Color is required")
    private String color;
    @NotNull(message = "Price is required")
    private Double rate;
    @NotNull(message = "Vehicle status is required")
    private VehicleStatus vehicleStatus;
    @NotNull(message = "Vehicle type is required")
    private VehicleType vehicleType;
    @OneToMany(mappedBy = "vehicle")
    private List<Reservation> reservations;
    @OneToMany(mappedBy = "vehicle")
    private List<Rental> rentals;
}
