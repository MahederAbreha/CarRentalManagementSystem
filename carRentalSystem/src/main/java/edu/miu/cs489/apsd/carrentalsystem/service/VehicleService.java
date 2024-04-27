package edu.miu.cs489.apsd.carrentalsystem.service;

import edu.miu.cs489.apsd.carrentalsystem.dto.vehicle.request.VehicleRequest;
import edu.miu.cs489.apsd.carrentalsystem.dto.vehicle.response.VehicleResponse;
import edu.miu.cs489.apsd.carrentalsystem.model.enums.VehicleStatus;
import org.w3c.dom.ls.LSInput;

import java.util.List;

public interface VehicleService {
    List<VehicleResponse> getAllVehicle() ;
    VehicleResponse getVehicleById(Long id) ;
    VehicleResponse createVehicle(VehicleRequest vehicle) ;
    VehicleResponse updateVehicle(VehicleRequest vehicle, Long id);
    void deleteVehicle(Long id) ;

    List<VehicleResponse> getAvailableVehicles();

}
