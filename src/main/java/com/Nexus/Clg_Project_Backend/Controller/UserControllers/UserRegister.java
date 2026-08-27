package com.Nexus.Clg_Project_Backend.Controller.UserControllers;

import com.Nexus.Clg_Project_Backend.DTO.UserDTO.UserRegisterRequestDTO;
import com.Nexus.Clg_Project_Backend.DTO.UserDTO.UserRegisterResponseDTO;
import com.Nexus.Clg_Project_Backend.Service.UserService.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class UserRegister {

    @Autowired
    AuthenticationService authService;

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponseDTO> register(
            @RequestBody UserRegisterRequestDTO userRegisterRequestDTO) {

            return authService.register(userRegisterRequestDTO);
    }
}
