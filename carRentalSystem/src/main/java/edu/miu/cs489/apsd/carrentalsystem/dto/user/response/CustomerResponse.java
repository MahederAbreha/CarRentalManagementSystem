package edu.miu.cs489.apsd.carrentalsystem.dto.user.response;

import edu.miu.cs489.apsd.carrentalsystem.dto.vehicle.response.ReservationResponse;
import edu.miu.cs489.apsd.carrentalsystem.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {
    Long userId;
    String firstName;
    String lastName;
    String email;
    String phoneNumber;
    Address address;
    List<RoleResponse> roles;
    String driversLicenceNumber;
    List<ReservationResponse> reservations;
}