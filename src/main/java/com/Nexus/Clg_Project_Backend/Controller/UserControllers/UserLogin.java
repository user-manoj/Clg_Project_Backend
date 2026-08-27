package com.Nexus.Clg_Project_Backend.Controller.UserControllers;

import com.Nexus.Clg_Project_Backend.DTO.UserDTO.LoginRequestDTO;
import com.Nexus.Clg_Project_Backend.DTO.UserDTO.LoginResponseDTO;
import com.Nexus.Clg_Project_Backend.Service.UserService.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
@CrossOrigin(origins = "http://localhost:5173")
public class UserLogin {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO) {

        Authentication authRequest =
                UsernamePasswordAuthenticationToken.unauthenticated(
                        loginRequestDTO.getUsername(),
                        loginRequestDTO.getPassword()
                );

        Authentication authResponse = authenticationManager.authenticate(authRequest);

        String token = jwtService.generateToken(authResponse);

        return new LoginResponseDTO(token);

    }
}
