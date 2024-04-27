package edu.miu.cs489.apsd.carrentalsystem.dto.user.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse2 {

        Long userId;
        String firstName;
        String lastName;
        String email;
        String phoneNumber;
        List<RoleResponse> roles;
        String driversLicenceNumber;

}
