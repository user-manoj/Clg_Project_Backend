package com.Nexus.Clg_Project_Backend.DTO.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminCreateUserRequestDTO {
    private String username;
    private String password;
    private String role; // "STUDENT", "LECTURER", or "ADMIN" — admin's choice
}