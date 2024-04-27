package edu.miu.cs489.apsd.carrentalsystem.service.impl;

import edu.miu.cs489.apsd.carrentalsystem.dto.user.request.AdminRequest;
import edu.miu.cs489.apsd.carrentalsystem.dto.user.request.AdminUpdateRequest;
import edu.miu.cs489.apsd.carrentalsystem.dto.user.response.AdminResponse;
import edu.miu.cs489.apsd.carrentalsystem.exception.UserException;
import edu.miu.cs489.apsd.carrentalsystem.model.enums.RoleType;
import edu.miu.cs489.apsd.carrentalsystem.model.users.Admin;
import edu.miu.cs489.apsd.carrentalsystem.model.users.Role;
import edu.miu.cs489.apsd.carrentalsystem.repository.UserDAO;
import edu.miu.cs489.apsd.carrentalsystem.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements UserService<AdminRequest, AdminUpdateRequest, AdminResponse> {
    private final UserDAO userDAO;
    private final ModelMapper mapper;

    public AdminServiceImpl(UserDAO userDAO, ModelMapper mapper) {
        this.userDAO = userDAO;
        this.mapper = mapper;
    }

    @Override
    public AdminResponse createUser(AdminRequest user) {
        Admin admin = mapper.map(user, Admin.class);
        return mapper.map(userDAO.save(admin), AdminResponse.class);
    }


    @Override
    public AdminResponse updateUser(Long id, AdminUpdateRequest user) {
        Admin admin = (Admin) userDAO.findByRoles_RoleTypeAndUserIdIs(RoleType.ADMIN, id)
                .orElseThrow(() -> new UserException("Admin with id "+ id +" not found"));
        admin.setFirstName(user.getFirstName());
        admin.setLastName(user.getLastName());
        admin.setEmail(user.getEmail());
        admin.setPhoneNumber(user.getPhoneNumber());
        admin.setAddress(user.getAddress());
        Role role = new Role();
        role.setRoleType(RoleType.ADMIN);
        admin.setRoles(List.of(role));
        return mapper.map(userDAO.save(admin), AdminResponse.class);

    }



    @Override
    public void deleteUser(Long id) {
        userDAO.findByRoles_RoleTypeAndUserIdIs(RoleType.ADMIN, id)
                .ifPresent(userDAO::delete);


    }

    @Override
    public AdminResponse getUserById(Long id) {
        Admin admin  = (Admin) userDAO.findByRoles_RoleTypeAndUserIdIs(RoleType.ADMIN, id)
                .orElseThrow(() -> new UserException("Admin with id "+ id +" not found"));
        return mapper.map(admin, AdminResponse.class);
    }

    @Override
    public List<AdminResponse> getAllUsers() {
        return userDAO.findAllByRoles_RoleType(RoleType.ADMIN)
                .stream()
                .map(admin -> mapper.map(admin, AdminResponse.class))
                .collect(Collectors.toList());
    }
}