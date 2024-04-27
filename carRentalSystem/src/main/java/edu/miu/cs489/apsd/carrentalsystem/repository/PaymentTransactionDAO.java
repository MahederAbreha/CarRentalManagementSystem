package edu.miu.cs489.apsd.carrentalsystem.repository;

import edu.miu.cs489.apsd.carrentalsystem.model.vehicle.PaymentTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentTransactionDAO extends JpaRepository<PaymentTransaction, Long> {
}
