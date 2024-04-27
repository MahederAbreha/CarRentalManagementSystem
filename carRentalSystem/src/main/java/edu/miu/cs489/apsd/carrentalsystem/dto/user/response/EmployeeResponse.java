package edu.miu.cs489.apsd.carrentalsystem.dto.user.response;

import edu.miu.cs489.apsd.carrentalsystem.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {
    Long userId;
    String firstName;
    String lastName;
    String email;
    String phoneNumber;
    Address address;
    List<RoleResponse> roles;
    String employeeId;
    LocalDate dob;
    String office;
}