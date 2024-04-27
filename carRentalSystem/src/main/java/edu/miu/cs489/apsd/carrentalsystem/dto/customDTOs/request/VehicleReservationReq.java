package edu.miu.cs489.apsd.carrentalsystem.dto.customDTOs.request;

import edu.miu.cs489.apsd.carrentalsystem.dto.customDTOs.request.RentalReservationReq;
import edu.miu.cs489.apsd.carrentalsystem.model.enums.VehicleStatus;
import edu.miu.cs489.apsd.carrentalsystem.model.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleReservationReq  {
    String plateNumber;
    String make;
    String model;
    Integer year;
    Long mileage;
    String color;
    Double price;
    VehicleStatus vehicleStatus;
    VehicleType vehicleType;
    List<RentalReservationReq> rentals;


}