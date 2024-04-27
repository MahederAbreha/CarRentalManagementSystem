package edu.miu.cs489.apsd.carrentalsystem.model.users;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee extends User {
    @Size(min = 6, max = 6, message = "Employee Id must be 6 characters")
    private String employeeId;
    private LocalDate dob;
    private String office;


}
