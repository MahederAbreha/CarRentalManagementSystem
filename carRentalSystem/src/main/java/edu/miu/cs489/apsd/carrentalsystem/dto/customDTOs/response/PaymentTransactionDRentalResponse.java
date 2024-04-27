package edu.miu.cs489.apsd.carrentalsystem.dto.customDTOs.response;

import edu.miu.cs489.apsd.carrentalsystem.dto.user.response.CustomerResponse2;
import edu.miu.cs489.apsd.carrentalsystem.dto.user.response.RoleResponse;
import edu.miu.cs489.apsd.carrentalsystem.model.enums.PaymentStatus;
import edu.miu.cs489.apsd.carrentalsystem.model.enums.PaymentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentTransactionDRentalResponse  {
    Long transactionId;
    Double amount;
    LocalDate paymentDate;
    PaymentType paymentType;
    PaymentStatus paymentStatus;
    CustomerResponse2 customer;


}