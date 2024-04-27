package edu.miu.cs489.apsd.carrentalsystem.service;

import edu.miu.cs489.apsd.carrentalsystem.dto.vehicle.response.PaymentTransactionResponse;
import edu.miu.cs489.apsd.carrentalsystem.dto.vehicle.request.PaymentTransactionRequest;

import java.util.List;

public interface PaymentTransactionService {

    PaymentTransactionResponse createPaymentTransaction(PaymentTransactionRequest request);

    PaymentTransactionResponse updatePaymentTransaction(Long id, PaymentTransactionRequest request);

    PaymentTransactionResponse getPaymentTransactionById(Long id);
    void deletePaymentTransaction(Long id);
    PaymentTransactionResponse refundPaymentTransactionByReservationId(Long id);
    PaymentTransactionResponse updatePaymentPrice(Long id, Double price);
    List<PaymentTransactionResponse> getAllPaymentTransactions();
}