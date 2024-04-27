package edu.miu.cs489.apsd.carrentalsystem.repository;

import edu.miu.cs489.apsd.carrentalsystem.model.vehicle.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationDAO extends JpaRepository<Reservation, Long> {
}
