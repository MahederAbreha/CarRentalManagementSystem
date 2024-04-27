package edu.miu.cs489.apsd.carrentalsystem.repository;

import edu.miu.cs489.apsd.carrentalsystem.model.enums.RoleType;
import edu.miu.cs489.apsd.carrentalsystem.model.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserDAO extends JpaRepository<User, Long> {
    List<User> findAllByRoles_RoleType(RoleType roleType);
    Optional<User> findByRoles_RoleTypeAndUserIdIs(RoleType roleType, Long userId);
    Optional<User> findUserByEmail(String email);
}
