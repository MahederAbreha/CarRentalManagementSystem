package edu.miu.cs489.apsd.carrentalsystem;

import edu.miu.cs489.apsd.carrentalsystem.dto.vehicle.response.VehicleResponse;
import edu.miu.cs489.apsd.carrentalsystem.exception.VehicleException;
import edu.miu.cs489.apsd.carrentalsystem.model.enums.VehicleStatus;
import edu.miu.cs489.apsd.carrentalsystem.model.enums.VehicleType;
import edu.miu.cs489.apsd.carrentalsystem.model.vehicle.Vehicle;
import edu.miu.cs489.apsd.carrentalsystem.repository.VehicleDAO;
import edu.miu.cs489.apsd.carrentalsystem.service.impl.VehicleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class VehicleServiceImplTest {
    @Mock
    private VehicleDAO vehicleDAO;

    @Mock
    private ModelMapper mapper;
    @InjectMocks
    private VehicleServiceImpl vehicleService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void getVehicleById_Found() {
        Long id = 1L;
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId(id);
        vehicle.setPlateNumber("ABC123");
        vehicle.setMake("Toyota");
        vehicle.setModel("Camry");
        vehicle.setYear(2021);
        vehicle.setMileage(1000L);
        vehicle.setColor("Black");
        vehicle.setRate(50.0);
        vehicle.setVehicleStatus(VehicleStatus.AVAILABLE);
        vehicle.setVehicleType(VehicleType.SEDAN);
        vehicle.setReservations(null);
        vehicle.setRentals(null);

        VehicleResponse vehicleResponse = new VehicleResponse();
        vehicleResponse.setVehicleId(id);
        vehicleResponse.setPlateNumber("ABC123");
        vehicleResponse.setMake("Toyota");
        vehicleResponse.setModel("Camry");
        vehicleResponse.setYear(2021);
        vehicleResponse.setMileage(1000L);
        vehicleResponse.setColor("Black");
        vehicleResponse.setRate(50.0);
        vehicleResponse.setVehicleStatus(VehicleStatus.AVAILABLE);
        vehicleResponse.setVehicleType(VehicleType.SEDAN);

        when(vehicleDAO.findById(id)).thenReturn(Optional.of(vehicle));
        when(mapper.map(vehicle, VehicleResponse.class)).thenReturn(vehicleResponse);

        VehicleResponse result = vehicleService.getVehicleById(id);

        assertNotNull(result);
        assertEquals(vehicleResponse, result);
        verify(vehicleDAO).findById(id);
        verify(mapper).map(vehicle, VehicleResponse.class);
    }
        @Test
        public void getVehicleById_NotFound() {

            Long id = 1L;
            when(vehicleDAO.findById(id)).thenReturn(Optional.empty());


            assertThrows(VehicleException.class, () -> {
                vehicleService.getVehicleById(id);
            });

            verify(vehicleDAO).findById(id);
            verify(mapper, never()).map(any(), eq(VehicleResponse.class));
        }


}
