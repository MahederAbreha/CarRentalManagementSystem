package edu.miu.cs489.apsd.carrentalsystem.dto.vehicle.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationResponse{
    Long reservationId;
    LocalDate reservationDate;
    LocalDate pickupDate;
    LocalDate returnDate;
}