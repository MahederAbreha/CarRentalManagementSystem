package edu.miu.cs489.apsd.carrentalsystem.service.impl;

import edu.miu.cs489.apsd.carrentalsystem.dto.vehicle.request.VehicleRequest;
import edu.miu.cs489.apsd.carrentalsystem.dto.vehicle.response.VehicleResponse;
import edu.miu.cs489.apsd.carrentalsystem.exception.VehicleException;
import edu.miu.cs489.apsd.carrentalsystem.model.enums.VehicleStatus;
import edu.miu.cs489.apsd.carrentalsystem.model.vehicle.Vehicle;
import edu.miu.cs489.apsd.carrentalsystem.repository.VehicleDAO;
import edu.miu.cs489.apsd.carrentalsystem.service.VehicleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class VehicleServiceImpl implements VehicleService {
    private final VehicleDAO vehicleDAO;
    private final ModelMapper mapper;

    public VehicleServiceImpl(VehicleDAO vehicleDAO, ModelMapper mapper) {
        this.vehicleDAO = vehicleDAO;
        this.mapper = mapper;
    }

    @Override
    public List<VehicleResponse> getAllVehicle() {
        return vehicleDAO.findAll().stream().map(vehicle -> mapper.map(vehicle, VehicleResponse.class)).toList();
    }
    @Override
    public VehicleResponse getVehicleById(Long id) {
        return mapper.map(vehicleDAO.findById(id)
                .orElseThrow(() -> new VehicleException("Vehicle with id " + id + " is not found")), VehicleResponse.class);
    }

    @Override
    public VehicleResponse createVehicle(VehicleRequest vehicle) {
        return mapper.map(vehicleDAO.save(mapper.map(vehicle, Vehicle.class)), VehicleResponse.class);
    }
    @Override
    public VehicleResponse updateVehicle(VehicleRequest vehicle, Long id) {
        Vehicle vehicleEntity = vehicleDAO.findById(id)
                .orElseThrow(() -> new VehicleException("Vehicle with id " + id + " is not found"));
        vehicleEntity.setMake(vehicle.getMake());
        vehicleEntity.setModel(vehicle.getModel());
        vehicleEntity.setYear(vehicle.getYear());
        vehicleEntity.setMileage(vehicle.getMileage());
        vehicleEntity.setColor(vehicle.getColor());
        vehicleEntity.setPlateNumber(vehicle.getPlateNumber());
        vehicleEntity.setVehicleStatus(vehicle.getVehicleStatus());
        vehicleEntity.setVehicleType(vehicle.getVehicleType());
        vehicleEntity.setRate(vehicle.getRate());
        return mapper.map(vehicleDAO.save(vehicleEntity), VehicleResponse.class);
    }

    @Override
    public void deleteVehicle(Long id) {
        vehicleDAO.findById(id).ifPresentOrElse(vehicleDAO::delete, () -> {
            throw new VehicleException("Vehicle with id " + id + " is not found");
        });
    }

    @Override
    public List<VehicleResponse> getAvailableVehicles() {
        return vehicleDAO.findAllByVehicleStatus(VehicleStatus.AVAILABLE).stream().map(vehicle -> mapper.map(vehicle, VehicleResponse.class)).toList();
    }

}
