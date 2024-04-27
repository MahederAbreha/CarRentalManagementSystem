package edu.miu.cs489.apsd.carrentalsystem.controller;

import edu.miu.cs489.apsd.carrentalsystem.dto.user.request.CustomerRequest;
import edu.miu.cs489.apsd.carrentalsystem.dto.user.request.CustomerUpdateRequest;
import edu.miu.cs489.apsd.carrentalsystem.dto.user.response.CustomerResponse;
import edu.miu.cs489.apsd.carrentalsystem.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    @Qualifier("customerService")
    private final UserService<CustomerRequest, CustomerUpdateRequest, CustomerResponse> customerService;

    public CustomerController(UserService<CustomerRequest, CustomerUpdateRequest, CustomerResponse> customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllUsers());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getUserById(id));
    }
    @PostMapping("/create")
    public ResponseEntity<?> createCustomer(@RequestBody CustomerRequest customer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.createUser(customer));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long id, @RequestBody CustomerUpdateRequest customer) {
        return ResponseEntity.ok(customerService.updateUser(id, customer));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        customerService.deleteUser(id);
        return ResponseEntity.ok("Customer with id " + id + " is deleted");
    }
}
