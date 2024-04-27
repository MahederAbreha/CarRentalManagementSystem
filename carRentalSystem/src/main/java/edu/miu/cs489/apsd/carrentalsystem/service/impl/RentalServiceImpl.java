package edu.miu.cs489.apsd.carrentalsystem.service.impl;

import edu.miu.cs489.apsd.carrentalsystem.dto.vehicle.request.RentalRequest;
import edu.miu.cs489.apsd.carrentalsystem.dto.vehicle.response.RentalResponse;
import edu.miu.cs489.apsd.carrentalsystem.exception.RentalException;
import edu.miu.cs489.apsd.carrentalsystem.model.enums.RentalStatus;
import edu.miu.cs489.apsd.carrentalsystem.model.vehicle.Rental;
import edu.miu.cs489.apsd.carrentalsystem.repository.RentalDAO;
import edu.miu.cs489.apsd.carrentalsystem.service.RentalService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RentalServiceImpl implements RentalService {
    private final RentalDAO rentalDAO;
    private final ModelMapper mapper;

    public RentalServiceImpl(RentalDAO rentalDAO, ModelMapper mapper) {
        this.rentalDAO = rentalDAO;
        this.mapper = mapper;
    }

    @Override
    public RentalResponse createRental(RentalRequest request) {
        Rental rentalEntity = mapper.map(request, Rental.class);
        Rental savedRental = rentalDAO.save(rentalEntity);
        return mapper.map(savedRental, RentalResponse.class);
    }

    @Override
    public RentalResponse updateRental(Long id, RentalRequest request) {
        Rental rentalEntity = rentalDAO.findById(id)
                .orElseThrow(() -> new RentalException("Rental with id " + id + " is not found"));
        rentalEntity.setRentalPrice(request.getRentalPrice());
        rentalEntity.setStartDate(request.getStartDate());
        rentalEntity.setEndDate(request.getEndDate());
        rentalEntity.setStatus(request.getStatus());
        return mapper.map(rentalDAO.save(rentalEntity), RentalResponse.class);
    }
    @Override
    public List<RentalResponse> getAllRentals() {
        return rentalDAO.findAll().stream().map(rental -> mapper.map(rental, RentalResponse.class)).toList();
    }

    @Override
    public RentalResponse getRentalById(Long id) {
        return mapper.map(rentalDAO.findById(id)
                .orElseThrow(() -> new RentalException("Rental with id " + id + " is not found")), RentalResponse.class);
    }

    @Override
    public RentalResponse refundRental(Long id) {
        Rental rental = rentalDAO.findById(id)
                .orElseThrow(() -> new RentalException("Rental with id " + id + " is not found"));
        rental.setStatus(RentalStatus.REFUNDED);
        return mapper.map(rentalDAO.save(rental), RentalResponse.class);
    }

    @Override
    public RentalResponse cancelRental(Long id) {
        Rental rental = rentalDAO.findById(id)
                .orElseThrow(() -> new RentalException("Rental with id " + id + " is not found"));
        rental.setStatus(RentalStatus.CANCELLED);
        return mapper.map(rentalDAO.save(rental), RentalResponse.class);
    }
}
