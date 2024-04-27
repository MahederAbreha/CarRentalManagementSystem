package edu.miu.cs489.apsd.carrentalsystem.controller;

import edu.miu.cs489.apsd.carrentalsystem.dto.user.request.EmployeeRequest;
import edu.miu.cs489.apsd.carrentalsystem.dto.user.request.EmployeeUpdateRequest;
import edu.miu.cs489.apsd.carrentalsystem.dto.user.response.EmployeeResponse;
import edu.miu.cs489.apsd.carrentalsystem.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    @Qualifier("employeeService")
    private final UserService<EmployeeRequest, EmployeeUpdateRequest, EmployeeResponse> employeeService;

    public EmployeeController(UserService<EmployeeRequest, EmployeeUpdateRequest, EmployeeResponse> employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllUsers());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getUserById(id));
    }
    @PostMapping("/create")
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeRequest employee) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.createUser(employee));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody EmployeeUpdateRequest employee) {
        return ResponseEntity.ok(employeeService.updateUser(id, employee));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteUser(id);
        return ResponseEntity.ok("Employee with id " + id + " is deleted");
    }
}
