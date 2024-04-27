package edu.miu.cs489.apsd.carrentalsystem.controller;

import edu.miu.cs489.apsd.carrentalsystem.dto.vehicle.request.VehicleRequest;
import edu.miu.cs489.apsd.carrentalsystem.model.enums.VehicleStatus;
import edu.miu.cs489.apsd.carrentalsystem.service.VehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vehicles")
public class VehicleController {
    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAllVehicles() {
        return ResponseEntity.ok(vehicleService.getAllVehicle());
    }
    @GetMapping("/available")
    public ResponseEntity<?> getAvailableVehicles() {
        return ResponseEntity.ok(vehicleService.getAvailableVehicles());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getVehicleById(@PathVariable Long id) {
        return ResponseEntity.ok(vehicleService.getVehicleById(id));
    }
    @PostMapping("/register")
    public ResponseEntity<?> createVehicle(@RequestBody VehicleRequest vehicle) {
        return ResponseEntity.ok(vehicleService.createVehicle(vehicle));
    }
    @PutMapping("/update/{id}")
public ResponseEntity<?> updateVehicle(@PathVariable Long id, @RequestBody VehicleRequest vehicle) {
        return ResponseEntity.ok(vehicleService.updateVehicle(vehicle, id));
    }

}
