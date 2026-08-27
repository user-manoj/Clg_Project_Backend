package com.Nexus.Clg_Project_Backend.Service.UserService;

import com.Nexus.Clg_Project_Backend.DTO.UserDTO.UserRegisterRequestDTO;
import com.Nexus.Clg_Project_Backend.DTO.UserDTO.UserRegisterResponseDTO;
import com.Nexus.Clg_Project_Backend.Model.UserModel.RoleEntity;
import com.Nexus.Clg_Project_Backend.Model.UserModel.UserEntity;
import com.Nexus.Clg_Project_Backend.Repo.UserRepository.RoleRepository;
import com.Nexus.Clg_Project_Backend.Repo.UserRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder pwdEncoder;

    @Autowired
    private RoleRepository roleRepo;

    public ResponseEntity<UserRegisterResponseDTO> register(UserRegisterRequestDTO userRegisterRequestDTO) {
        if (userRepository.findByUsername(userRegisterRequestDTO.getUsername()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        UserEntity user = new UserEntity();

        user.setUsername(userRegisterRequestDTO.getUsername());

        String encodedPwd = pwdEncoder.encode(userRegisterRequestDTO.getPassword());
        user.setPassword(encodedPwd);

        user.setEnabled(true);

        RoleEntity role = roleRepo.findByName("ROLE_USER").get();

        user.getRoles().add(role);

        userRepository.save(user);

        UserRegisterResponseDTO ResponseDTO = new UserRegisterResponseDTO();

        ResponseDTO.setUserName(user.getUsername());
        ResponseDTO.setMessage("Successfully registered");

        return new ResponseEntity<>(ResponseDTO, HttpStatus.OK);
    }
}
