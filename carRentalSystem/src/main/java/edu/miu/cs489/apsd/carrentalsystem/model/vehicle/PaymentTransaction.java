package edu.miu.cs489.apsd.carrentalsystem.model.vehicle;

import edu.miu.cs489.apsd.carrentalsystem.model.enums.PaymentStatus;
import edu.miu.cs489.apsd.carrentalsystem.model.enums.PaymentType;
import edu.miu.cs489.apsd.carrentalsystem.model.users.Customer;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;
    @NotNull(message = "Amount is required")
    private Double amount;
    @NotNull(message = "Payment Date is required")
    private LocalDate paymentDate;
    @NotNull(message = "Payment Type is required")
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
    @NotNull(message = "Transaction Id is mandatory")
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    @ManyToOne
    private Customer customer;
    @OneToOne
    @JoinColumn(name = "rental_id")
    private Rental rental;

}

