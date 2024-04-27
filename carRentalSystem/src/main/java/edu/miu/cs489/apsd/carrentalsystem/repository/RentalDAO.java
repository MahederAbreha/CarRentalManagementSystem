package edu.miu.cs489.apsd.carrentalsystem.repository;

import edu.miu.cs489.apsd.carrentalsystem.model.vehicle.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalDAO extends JpaRepository<Rental, Long>{
}
