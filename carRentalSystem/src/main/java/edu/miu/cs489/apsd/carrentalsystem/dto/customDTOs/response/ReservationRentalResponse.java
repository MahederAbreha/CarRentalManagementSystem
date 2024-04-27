package edu.miu.cs489.apsd.carrentalsystem.dto.customDTOs.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRentalResponse  {
    Long reservationId;
    LocalDate reservationDate;
    LocalDate pickupDate;
    LocalDate returnDate;
}