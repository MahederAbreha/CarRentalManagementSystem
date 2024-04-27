package edu.miu.cs489.apsd.carrentalsystem.dto.vehicle.request;

import edu.miu.cs489.apsd.carrentalsystem.dto.customDTOs.request.CustomerReservationReq;
import edu.miu.cs489.apsd.carrentalsystem.dto.customDTOs.request.VehicleReservationReq;
import edu.miu.cs489.apsd.carrentalsystem.model.Address;
import edu.miu.cs489.apsd.carrentalsystem.model.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequest{
    CustomerReservationReq customer;
    PaymentType paymentType;
    Long vehicleId;
    LocalDate reservationDate;
    LocalDate pickupDate;
    LocalDate returnDate;
    Address pickupLocation;
    Address returnLocation;

}