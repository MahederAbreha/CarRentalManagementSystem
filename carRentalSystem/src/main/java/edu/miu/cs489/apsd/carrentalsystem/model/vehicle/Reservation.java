package edu.miu.cs489.apsd.carrentalsystem.model.vehicle;

import edu.miu.cs489.apsd.carrentalsystem.model.users.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;
    private LocalDate reservationDate;
    private LocalDate pickupDate;
    private LocalDate returnDate;
    @ManyToOne(cascade = CascadeType.ALL)
    private Customer customer;
    @ManyToOne
    private Vehicle vehicle;
    @OneToOne(cascade = CascadeType.ALL)
    private PaymentTransaction payment;

}
