package edu.miu.cs489.apsd.carrentalsystem.controller;

import edu.miu.cs489.apsd.carrentalsystem.dto.vehicle.request.PaymentTransactionRequest;
import edu.miu.cs489.apsd.carrentalsystem.service.PaymentTransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentTransactionService paymentService;

    public PaymentController(PaymentTransactionService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPaymentTransactions());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getPaymentById(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.getPaymentTransactionById(id));
    }
    @PostMapping("/create")
    public ResponseEntity<?> createPayment(PaymentTransactionRequest payment) {
        return ResponseEntity.ok(paymentService.createPaymentTransaction(payment));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updatePayment(@PathVariable Long id, @RequestBody PaymentTransactionRequest payment) {
        return ResponseEntity.ok(paymentService.updatePaymentTransaction(id, payment));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePayment(@PathVariable Long id) {
        paymentService.deletePaymentTransaction(id);
        return ResponseEntity.ok("Payment with id " + id + " is deleted");
    }
    @PatchMapping("/refund/{id}")
    public ResponseEntity<?> refundPayment(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.refundPaymentTransactionByReservationId(id));
    }
    @PatchMapping("/updatePrice/{id}")
    public ResponseEntity<?> updatePrice(@PathVariable Long id, @RequestBody Double price) {
        return ResponseEntity.ok(paymentService.updatePaymentPrice(id, price));
    }

}
