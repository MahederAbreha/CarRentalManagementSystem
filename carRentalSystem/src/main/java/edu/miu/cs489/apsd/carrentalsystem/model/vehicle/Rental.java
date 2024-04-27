package edu.miu.cs489.apsd.carrentalsystem.model.vehicle;

import edu.miu.cs489.apsd.carrentalsystem.model.enums.PaymentStatus;
import edu.miu.cs489.apsd.carrentalsystem.model.enums.PaymentType;
import edu.miu.cs489.apsd.carrentalsystem.model.enums.RentalStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rentalId;
    @NotNull(message = "Rental Price is required")
    private Double rentalPrice;
    private LocalDate startDate;
    private LocalDate endDate;
    @NotNull(message = "Status is required")
    private RentalStatus status;
    @ManyToOne
    private Vehicle vehicle;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "rental")
    private PaymentTransaction paymentTransaction;
    @OneToOne
    private Reservation reservation;

}
