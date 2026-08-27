package com.Nexus.Clg_Project_Backend.DTO.UserDTO;

import lombok.Data;

@Data
public class UserProfileDTO {
    private String username; // read-only — comes from the token, never edited
    private String role;
    private String name;
}