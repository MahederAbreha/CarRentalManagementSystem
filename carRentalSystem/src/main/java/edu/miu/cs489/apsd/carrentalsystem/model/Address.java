package edu.miu.cs489.apsd.carrentalsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long addressId;
     private String street;
     private String city;
     private String state;
     private String zipCode;
     private String country;
}
