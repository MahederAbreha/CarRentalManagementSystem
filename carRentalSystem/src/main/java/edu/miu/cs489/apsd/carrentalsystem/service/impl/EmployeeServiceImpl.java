package edu.miu.cs489.apsd.carrentalsystem.service.impl;

import edu.miu.cs489.apsd.carrentalsystem.dto.user.request.EmployeeRequest;
import edu.miu.cs489.apsd.carrentalsystem.dto.user.request.EmployeeUpdateRequest;
import edu.miu.cs489.apsd.carrentalsystem.dto.user.response.EmployeeResponse;
import edu.miu.cs489.apsd.carrentalsystem.exception.UserException;
import edu.miu.cs489.apsd.carrentalsystem.model.enums.RoleType;
import edu.miu.cs489.apsd.carrentalsystem.model.users.Employee;
import edu.miu.cs489.apsd.carrentalsystem.model.users.Role;
import edu.miu.cs489.apsd.carrentalsystem.repository.UserDAO;
import edu.miu.cs489.apsd.carrentalsystem.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements UserService<EmployeeRequest, EmployeeUpdateRequest, EmployeeResponse> {

    private final UserDAO userDAO;
    private final ModelMapper mapper;

    public EmployeeServiceImpl(UserDAO userDAO, ModelMapper mapper) {
        this.userDAO = userDAO;
        this.mapper = mapper;
    }

    @Override
    public EmployeeResponse createUser(EmployeeRequest user) {
        Employee userEntity = mapper.map(user, Employee.class);
        Employee savedUser = userDAO.save(userEntity);
        return mapper.map(savedUser, EmployeeResponse.class);
    }

    @Override
    public EmployeeResponse updateUser(Long id, EmployeeUpdateRequest user) {
        Employee employee = (Employee) userDAO.findByRoles_RoleTypeAndUserIdIs(RoleType.EMPLOYEE, id)
                .orElseThrow(() -> new UserException("Employee with id " + id + " not found"));
        employee.setFirstName(user.getFirstName());
        employee.setLastName(user.getLastName());
        employee.setPhoneNumber(user.getPhoneNumber());
        employee.setEmail(user.getEmail());
        employee.setAddress(user.getAddress());
        Role role = new Role();
        role.setRoleType(RoleType.EMPLOYEE);
        employee.setRoles(List.of(role));
        employee.setDob(user.getDob());
        employee.setOffice(user.getOffice());
        return mapper.map(userDAO.save(employee), EmployeeResponse.class);

    }

    @Override
    public void deleteUser(Long id) {
        userDAO.findByRoles_RoleTypeAndUserIdIs(RoleType.EMPLOYEE, id)
                .ifPresentOrElse(userDAO::delete, () -> {
                    throw new UserException("Employee with id " + id + " not found");
                });
    }

    @Override
    public EmployeeResponse getUserById(Long id) {
        Employee employee = (Employee) userDAO.findByRoles_RoleTypeAndUserIdIs(RoleType.EMPLOYEE, id)
                .orElseThrow(() -> new UserException("Employee with id " + id + " not found"));
        return mapper.map(employee, EmployeeResponse.class);
    }

    @Override
    public List<EmployeeResponse> getAllUsers() {
        return userDAO.findAllByRoles_RoleType(RoleType.EMPLOYEE).stream()
                .map(user -> mapper.map(user, EmployeeResponse.class)).toList();
    }
}
