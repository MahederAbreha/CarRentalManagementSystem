package edu.miu.cs489.apsd.carrentalsystem.dto.user.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthRequest {
    private String email;
    private String password;
}
