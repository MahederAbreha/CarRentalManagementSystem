package edu.miu.cs489.apsd.carrentalsystem.dto.user.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthResponse {
    private String email;
    private String password;
    private List<RoleResponse> roles;
}
