package edu.miu.cs489.apsd.carrentalsystem.service;

import edu.miu.cs489.apsd.carrentalsystem.dto.vehicle.request.ReservationRequest;
import edu.miu.cs489.apsd.carrentalsystem.dto.vehicle.response.ReservationResponse;

import java.time.LocalDate;
import java.util.List;

public interface ReservationService {
    ReservationResponse createReservation(ReservationRequest reservationRequest);
    ReservationResponse getReservation(Long id);
    List<ReservationResponse> getAllReservations();
    ReservationResponse updateReservation(Long id, ReservationRequest reservationRequest);
    void deleteReservation(Long id);
    ReservationResponse extendReservation(Long id, LocalDate newReturnDate);
}
