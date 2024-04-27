package edu.miu.cs489.apsd.carrentalsystem.controller;

import edu.miu.cs489.apsd.carrentalsystem.dto.vehicle.request.RentalRequest;
import edu.miu.cs489.apsd.carrentalsystem.service.RentalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rentals")
public class RentalController {
    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAllRentals() {
        return ResponseEntity.ok(rentalService.getAllRentals());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getRentalById(@PathVariable Long id) {
        return ResponseEntity.ok(rentalService.getRentalById(id));
    }
    @PostMapping("/create")
    public ResponseEntity<?> createRental(@RequestBody RentalRequest rental) {
        return ResponseEntity.ok(rentalService.createRental(rental));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateRental(@PathVariable Long id, @RequestBody RentalRequest rental) {
        return ResponseEntity.ok(rentalService.updateRental(id, rental));
    }
    @PatchMapping("/refund/{id}")
    public ResponseEntity<?> refundRental(@PathVariable Long id) {
        return ResponseEntity.ok(rentalService.refundRental(id));
    }
    @PatchMapping("/cancel/{id}")
    public ResponseEntity<?> cancelRental(@PathVariable Long id) {
        return ResponseEntity.ok(rentalService.cancelRental(id));
    }

}
