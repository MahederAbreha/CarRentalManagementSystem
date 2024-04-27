package edu.miu.cs489.apsd.carrentalsystem.service.impl;

import edu.miu.cs489.apsd.carrentalsystem.dto.vehicle.response.PaymentTransactionResponse;
import edu.miu.cs489.apsd.carrentalsystem.dto.vehicle.request.PaymentTransactionRequest;
import edu.miu.cs489.apsd.carrentalsystem.exception.ReservationException;
import edu.miu.cs489.apsd.carrentalsystem.model.enums.PaymentStatus;
import edu.miu.cs489.apsd.carrentalsystem.model.vehicle.PaymentTransaction;
import edu.miu.cs489.apsd.carrentalsystem.model.vehicle.Reservation;
import edu.miu.cs489.apsd.carrentalsystem.repository.PaymentTransactionDAO;
import edu.miu.cs489.apsd.carrentalsystem.repository.ReservationDAO;
import edu.miu.cs489.apsd.carrentalsystem.service.PaymentTransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class PaymentTransactionServiceImpl implements PaymentTransactionService {
    private final PaymentTransactionDAO paymentTransactionDAO;
    private final ReservationDAO reservationDAO;
    private final ModelMapper mapper;

    public PaymentTransactionServiceImpl(PaymentTransactionDAO paymentTransactionDAO, ReservationDAO reservationDAO, ModelMapper mapper) {
        this.paymentTransactionDAO = paymentTransactionDAO;
        this.reservationDAO = reservationDAO;
        this.mapper = mapper;
    }

    @Override
    public PaymentTransactionResponse createPaymentTransaction(PaymentTransactionRequest request) {
        Reservation reservation = reservationDAO.findById(request.getTransactionId())
                .orElseThrow(() -> new ReservationException("Reservation with id " + request.getTransactionId() + " is not found"));
        PaymentTransaction paymentTransaction = reservation.getPayment();
        paymentTransaction.setPaymentDate(LocalDate.now());
        paymentTransaction.setAmount(request.getAmount());
        paymentTransaction.setPaymentType(request.getPaymentType());
        paymentTransaction.setPaymentStatus(request.getPaymentStatus());
        paymentTransaction.setCustomer(reservation.getCustomer());
        PaymentTransaction savedPaymentTransaction = paymentTransactionDAO.save(paymentTransaction);

        return mapper.map(savedPaymentTransaction, PaymentTransactionResponse.class);
    }

    @Override
    public PaymentTransactionResponse updatePaymentTransaction(Long id, PaymentTransactionRequest request) {
        PaymentTransaction paymentTransaction = paymentTransactionDAO.findById(id)
                .orElseThrow(() -> new ReservationException("Payment Transaction with id " + id + " is not found"));
        paymentTransaction.setAmount(request.getAmount());
        paymentTransaction.setPaymentDate(LocalDate.now());
        paymentTransaction.setPaymentType(request.getPaymentType());
        paymentTransaction.setPaymentStatus(request.getPaymentStatus());
        PaymentTransaction savedPaymentTransaction = paymentTransactionDAO.save(paymentTransaction);

        return mapper.map(savedPaymentTransaction, PaymentTransactionResponse.class);
    }

    @Override
    public PaymentTransactionResponse getPaymentTransactionById(Long id) {

        return mapper.map(paymentTransactionDAO.findById(id)
                .orElseThrow(() -> new ReservationException("Payment Transaction with id " + id + " is not found")), PaymentTransactionResponse.class);
    }

    @Override
    public void deletePaymentTransaction(Long id) {
        paymentTransactionDAO.findById(id)
                .orElseThrow(() -> new ReservationException("Payment Transaction with id " + id + " is not found"));
        paymentTransactionDAO.deleteById(id);

    }

    @Override
    public PaymentTransactionResponse refundPaymentTransactionByReservationId(Long id) {
        Reservation reservation = reservationDAO.findById(id)
                .orElseThrow(() -> new ReservationException("Reservation with id " + id + " is not found"));
        PaymentTransaction paymentTransaction = reservation.getPayment();
        paymentTransaction.setPaymentStatus(PaymentStatus.REFUNDED);
        PaymentTransaction savedPaymentTransaction = paymentTransactionDAO.save(paymentTransaction);
        return mapper.map(savedPaymentTransaction, PaymentTransactionResponse.class);
    }

    @Override
    public PaymentTransactionResponse updatePaymentPrice(Long id, Double price) {
        PaymentTransaction paymentTransaction = paymentTransactionDAO.findById(id)
                .orElseThrow(() -> new ReservationException("Payment Transaction with id " + id + " is not found"));
        paymentTransaction.setAmount(price);
        PaymentTransaction savedPaymentTransaction = paymentTransactionDAO.save(paymentTransaction);
        return mapper.map(savedPaymentTransaction, PaymentTransactionResponse.class);
    }

    @Override
    public List<PaymentTransactionResponse> getAllPaymentTransactions() {
        return paymentTransactionDAO.findAll().stream().map(paymentTransaction -> mapper.map(paymentTransaction, PaymentTransactionResponse.class)).toList();
    }
}
