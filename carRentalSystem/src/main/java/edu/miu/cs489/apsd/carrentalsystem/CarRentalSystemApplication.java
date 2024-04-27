package edu.miu.cs489.apsd.carrentalsystem;

import edu.miu.cs489.apsd.carrentalsystem.model.enums.RoleType;
import edu.miu.cs489.apsd.carrentalsystem.model.users.Admin;
import edu.miu.cs489.apsd.carrentalsystem.model.users.Role;
import edu.miu.cs489.apsd.carrentalsystem.repository.UserDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class CarRentalSystemApplication implements CommandLineRunner {
    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;

    public CarRentalSystemApplication(UserDAO userDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }


    public static void main(String[] args) {
        SpringApplication.run(CarRentalSystemApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Admin admin = new Admin();
        admin.setAdminId("AD1000");
        admin.setFirstName("Admin");
        admin.setLastName("Admin");
        admin.setEmail("admin@carrental.com");
        admin.setPhoneNumber("1234567890");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setRoles(List.of(new Role(null, RoleType.ADMIN, null)));
        userDAO.save(admin);
    }
}
