package edu.miu.cs489.apsd.carrentalsystem.dto.user.request;

import edu.miu.cs489.apsd.carrentalsystem.model.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminRequest {

    String adminId;
    String firstName;
    String lastName;
    String email;
    String password;
    String phoneNumber;
    Address address;
}