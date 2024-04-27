package edu.miu.cs489.apsd.carrentalsystem.service;

import edu.miu.cs489.apsd.carrentalsystem.dto.vehicle.request.RentalRequest;
import edu.miu.cs489.apsd.carrentalsystem.dto.vehicle.response.RentalResponse;

import java.util.List;

public interface RentalService {

    List<RentalResponse> getAllRentals();

    RentalResponse getRentalById(Long id);

    RentalResponse createRental(RentalRequest request);

    RentalResponse updateRental(Long id, RentalRequest request);


    RentalResponse refundRental(Long id);

    RentalResponse cancelRental(Long id);
}
