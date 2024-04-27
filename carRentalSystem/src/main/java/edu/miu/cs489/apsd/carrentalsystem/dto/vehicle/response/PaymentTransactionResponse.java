package edu.miu.cs489.apsd.carrentalsystem.dto.vehicle.response;

import edu.miu.cs489.apsd.carrentalsystem.model.enums.PaymentStatus;
import edu.miu.cs489.apsd.carrentalsystem.model.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentTransactionResponse {
    private Long transactionId;
    private Double amount;
    private LocalDate paymentDate;
    private PaymentType paymentType;
    private PaymentStatus paymentStatus;
}
