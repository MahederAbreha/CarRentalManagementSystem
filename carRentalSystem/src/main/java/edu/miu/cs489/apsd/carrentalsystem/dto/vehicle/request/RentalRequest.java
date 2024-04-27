package edu.miu.cs489.apsd.carrentalsystem.dto.vehicle.request;

import edu.miu.cs489.apsd.carrentalsystem.model.enums.RentalStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalRequest {
    private Double rentalPrice;
    private LocalDate startDate;
    private LocalDate endDate;
    private RentalStatus status;
    private Long vehicleId;
    private Long paymentTransactionId;
}
