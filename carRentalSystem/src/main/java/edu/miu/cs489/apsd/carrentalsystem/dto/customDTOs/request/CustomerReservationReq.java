package edu.miu.cs489.apsd.carrentalsystem.dto.customDTOs.request;

import edu.miu.cs489.apsd.carrentalsystem.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerReservationReq  {

    String firstName;
    String lastName;
    String email;
    String phoneNumber;
    Address address;
    String driversLicenceNumber;
}