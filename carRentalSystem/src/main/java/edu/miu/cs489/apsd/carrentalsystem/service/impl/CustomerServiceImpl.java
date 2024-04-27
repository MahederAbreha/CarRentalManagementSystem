package edu.miu.cs489.apsd.carrentalsystem.service.impl;

import edu.miu.cs489.apsd.carrentalsystem.dto.user.request.CustomerRequest;
import edu.miu.cs489.apsd.carrentalsystem.dto.user.request.CustomerUpdateRequest;
import edu.miu.cs489.apsd.carrentalsystem.dto.user.response.CustomerResponse;
import edu.miu.cs489.apsd.carrentalsystem.exception.UserException;
import edu.miu.cs489.apsd.carrentalsystem.model.enums.RoleType;
import edu.miu.cs489.apsd.carrentalsystem.model.users.Customer;
import edu.miu.cs489.apsd.carrentalsystem.model.users.Role;
import edu.miu.cs489.apsd.carrentalsystem.repository.UserDAO;
import edu.miu.cs489.apsd.carrentalsystem.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements UserService<CustomerRequest, CustomerUpdateRequest, CustomerResponse> {

    private final UserDAO userDAO;
    private final ModelMapper mapper;

    public CustomerServiceImpl(UserDAO userDAO, ModelMapper mapper) {
        this.userDAO = userDAO;
        this.mapper = mapper;
    }

    @Override
    public CustomerResponse createUser(CustomerRequest user) {
        Customer userEntity = mapper.map(user, Customer.class);
        Role role = new Role();
        role.setRoleType(RoleType.CUSTOMER);
        userEntity.setRoles(List.of(role));
        Customer savedUser = userDAO.save(userEntity);
        return mapper.map(savedUser, CustomerResponse.class);
    }

    @Override
    public CustomerResponse updateUser(Long id, CustomerUpdateRequest user) {
        Customer customer = (Customer) userDAO.findByRoles_RoleTypeAndUserIdIs(RoleType.CUSTOMER, id)
                .orElseThrow(() -> new UserException("Customer with id " + id + " not found"));
        customer.setFirstName(user.getFirstName());
        customer.setLastName(user.getLastName());
        customer.setPhoneNumber(user.getPhoneNumber());
        customer.setEmail(user.getEmail());
        customer.setAddress(user.getAddress());
        Role role = new Role();
        role.setRoleType(RoleType.CUSTOMER);
        customer.setRoles(List.of(role));
        customer.setDriversLicenceNumber(user.getDriversLicenceNumber());
        return mapper.map(userDAO.save(customer), CustomerResponse.class);

    }

    @Override
    public void deleteUser(Long id) {
        userDAO.findByRoles_RoleTypeAndUserIdIs(RoleType.CUSTOMER, id)
                .ifPresent(userDAO::delete);

    }

    @Override
    public CustomerResponse getUserById(Long id) {
        Customer customer = (Customer) userDAO.findByRoles_RoleTypeAndUserIdIs(RoleType.CUSTOMER, id)
                .orElseThrow(() -> new UserException("Customer with id " + id + " not found"));
        return mapper.map(customer, CustomerResponse.class);
    }

    @Override
    public List<CustomerResponse> getAllUsers() {
        return userDAO.findAllByRoles_RoleType(RoleType.CUSTOMER).stream().map(user -> mapper.map(user, CustomerResponse.class)).toList();
    }
}
