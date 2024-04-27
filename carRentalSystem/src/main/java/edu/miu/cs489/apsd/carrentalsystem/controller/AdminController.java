package edu.miu.cs489.apsd.carrentalsystem.controller;

import edu.miu.cs489.apsd.carrentalsystem.dto.user.request.AdminRequest;
import edu.miu.cs489.apsd.carrentalsystem.dto.user.request.AdminUpdateRequest;
import edu.miu.cs489.apsd.carrentalsystem.dto.user.response.AdminResponse;
import edu.miu.cs489.apsd.carrentalsystem.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admins")
public class AdminController {
    @Qualifier("adminService")
    private final UserService<AdminRequest, AdminUpdateRequest, AdminResponse> adminService;

    public AdminController(UserService<AdminRequest, AdminUpdateRequest, AdminResponse> adminService) {
        this.adminService = adminService;
    }


    @GetMapping("/all")
    public ResponseEntity<?> getAllAdmins() {
        return ResponseEntity.ok(adminService.getAllUsers());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getAdminById(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.getUserById(id));
    }
    @PostMapping("/create")
    public ResponseEntity<?> createAdmin(@RequestBody AdminRequest admin) {
        return ResponseEntity.status(HttpStatus.CREATED).body(adminService.createUser(admin));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAdmin(@PathVariable Long id, @RequestBody AdminUpdateRequest admin) {
        return ResponseEntity.ok(adminService.updateUser(id, admin));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable Long id) {
        adminService.deleteUser(id);
        return ResponseEntity.ok("Admin with id " + id + " is deleted");
    }

}
