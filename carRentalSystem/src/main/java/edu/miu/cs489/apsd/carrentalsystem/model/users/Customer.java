package edu.miu.cs489.apsd.carrentalsystem.model.users;

import edu.miu.cs489.apsd.carrentalsystem.model.vehicle.Reservation;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends User {

    @NotNull
    private String driversLicenceNumber;
    @OneToMany(mappedBy = "customer")
    private List<Reservation> reservations;


}
