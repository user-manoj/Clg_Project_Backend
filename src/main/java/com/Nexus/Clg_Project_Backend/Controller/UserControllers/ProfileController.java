package com.Nexus.Clg_Project_Backend.Controller.UserControllers;

import com.Nexus.Clg_Project_Backend.DTO.UserDTO.UserProfileDTO;
import com.Nexus.Clg_Project_Backend.Model.UserModel.UserEntity;
import com.Nexus.Clg_Project_Backend.Repo.UserRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@CrossOrigin(origins = "http://localhost:5173")
public class ProfileController {

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/me")
    public ResponseEntity<UserProfileDTO> getMyProfile(Authentication authentication) {
        UserEntity user = userRepo.findByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserProfileDTO dto = new UserProfileDTO();
        dto.setUsername(user.getUsername());
        dto.setName(user.getName());
        dto.setRole(user.getRoles().stream().findFirst().map(r -> r.getName()).orElse(""));

        return ResponseEntity.ok(dto);
    }

    @PutMapping("/me")
    public ResponseEntity<UserProfileDTO> updateMyName(
            Authentication authentication, @RequestBody UserProfileDTO update) {

        UserEntity user = userRepo.findByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(update.getName()); // only the name can be changed — username/role are ignored even if sent
        userRepo.save(user);

        return getMyProfile(authentication);
    }
}