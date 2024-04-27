package edu.miu.cs489.apsd.carrentalsystem.dto.vehicle.response;

import edu.miu.cs489.apsd.carrentalsystem.dto.customDTOs.response.PaymentTransactionDRentalResponse;
import edu.miu.cs489.apsd.carrentalsystem.dto.customDTOs.response.ReservationRentalResponse;
import edu.miu.cs489.apsd.carrentalsystem.model.enums.RentalStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalResponse  {
    Long rentalId;
    Double rentalPrice;
    LocalDate startDate;
    LocalDate endDate;
    RentalStatus status;
    VehicleRentalResponse vehicle;
    PaymentTransactionDRentalResponse paymentTransaction;
    ReservationRentalResponse reservation;
}