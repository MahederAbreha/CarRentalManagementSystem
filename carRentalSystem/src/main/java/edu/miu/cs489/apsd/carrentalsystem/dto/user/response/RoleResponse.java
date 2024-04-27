package edu.miu.cs489.apsd.carrentalsystem.dto.user.response;

import edu.miu.cs489.apsd.carrentalsystem.model.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleResponse  {
    Long roleId;
    RoleType roleType;
}