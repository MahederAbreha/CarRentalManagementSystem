package edu.miu.cs489.apsd.carrentalsystem.model.users;

import edu.miu.cs489.apsd.carrentalsystem.model.enums.RoleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;
    private RoleType roleType;
    @ManyToMany(mappedBy = "roles")
    private List<User> users;
}
