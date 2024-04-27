package edu.miu.cs489.apsd.carrentalsystem.security;

import edu.miu.cs489.apsd.carrentalsystem.dto.user.response.RoleResponse;
import edu.miu.cs489.apsd.carrentalsystem.repository.UserDAO;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalUserDetailService implements UserDetailsService {
    private final UserDAO userDAO;
    private final ModelMapper mapper;

    public RentalUserDetailService(UserDAO userDAO, @Lazy ModelMapper mapper) {
        this.userDAO = userDAO;
        this.mapper = mapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userDAO.findUserByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + username + " is not found"));
        String userName = user.getEmail();
        String password = user.getPassword();
        List<RoleResponse> roles = user.getRoles().stream().map(role -> mapper.map(role, RoleResponse.class))
                .toList();
        String allRoles = roles.stream().map(role -> role.getRoleType().name()).reduce("", (a, b) -> a + "," + b);
        return User.withUsername(userName)
                .password(password)
                .roles(allRoles)
                .build();
    }
}
