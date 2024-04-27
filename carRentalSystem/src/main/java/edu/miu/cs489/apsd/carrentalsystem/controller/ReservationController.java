package edu.miu.cs489.apsd.carrentalsystem.controller;

import edu.miu.cs489.apsd.carrentalsystem.dto.vehicle.request.ReservationRequest;
import edu.miu.cs489.apsd.carrentalsystem.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllReservations() {
        return ResponseEntity.ok(reservationService.getAllReservations());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getReservationById(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.getReservation(id));
    }
    @PostMapping("/create")
    public ResponseEntity<?> createReservation(@RequestBody ReservationRequest reservation) {
        return ResponseEntity.ok(reservationService.createReservation(reservation));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.ok("Reservation with id " + id + " is deleted");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateReservation(@PathVariable Long id, @RequestBody ReservationRequest reservation) {
        return ResponseEntity.ok(reservationService.updateReservation(id, reservation));
    }
    @PutMapping("/extend/{id}")
    public ResponseEntity<?> extendReservation(@PathVariable Long id, @RequestBody LocalDate newReturnDate) {
        return ResponseEntity.ok(reservationService.extendReservation(id, newReturnDate));
    }
}
