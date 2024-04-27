package edu.miu.cs489.apsd.carrentalsystem.dto.user.request;

import edu.miu.cs489.apsd.carrentalsystem.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {

    String firstName;
    String lastName;
    String email;
    String password;
    String phoneNumber;
    Address address;
    String employeeId;
    LocalDate dob;
    String office;
}