package edu.miu.cs489.apsd.carrentalsystem.model.users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin extends User {
    @NotBlank(message = "Admin Id is mandatory")
    @Column(unique = true)
    private String adminId;
}
