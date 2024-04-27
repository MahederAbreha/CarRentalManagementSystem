package edu.miu.cs489.apsd.carrentalsystem.controller;

import edu.miu.cs489.apsd.carrentalsystem.dto.user.request.UserAuthRequest;
import edu.miu.cs489.apsd.carrentalsystem.security.util.JWTMgmtUtilityService;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class UserAuthController {
    private final JWTMgmtUtilityService jwtMgmtUtilityService;
    private final AuthenticationManager authenticationManager;

    public UserAuthController(JWTMgmtUtilityService jwtMgmtUtilityService, AuthenticationManager authenticationManager) {
        this.jwtMgmtUtilityService = jwtMgmtUtilityService;
        this.authenticationManager = authenticationManager;
    }
    @PostMapping("/login")
    public String authenticateUser(@Valid @RequestBody UserAuthRequest userAuthRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userAuthRequest.getEmail(), userAuthRequest.getPassword()));
        return jwtMgmtUtilityService.generateToken(userAuthRequest.getEmail());
    }
}
