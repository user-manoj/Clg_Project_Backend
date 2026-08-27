package com.Nexus.Clg_Project_Backend.Service.UserService;

import com.Nexus.Clg_Project_Backend.DTO.UserDTO.AdminCreateUserRequestDTO;
import com.Nexus.Clg_Project_Backend.Model.UserModel.RoleEntity;
import com.Nexus.Clg_Project_Backend.Model.UserModel.UserEntity;
import com.Nexus.Clg_Project_Backend.Repo.UserRepository.RoleRepository;
import com.Nexus.Clg_Project_Backend.Repo.UserRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String createUser(AdminCreateUserRequestDTO request) {
        if (userRepo.findByUsername(request.getUsername()).isPresent()) {
            return "Username already exists";
        }

        UserEntity user = new UserEntity();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEnabled(true);

        String roleName = "ROLE_" + request.getRole().toUpperCase();
        RoleEntity role = roleRepo.findByName(roleName)
                .orElseGet(() -> roleRepo.save(new RoleEntity(null, roleName)));

        user.setRoles(new HashSet<>(Set.of(role)));
        userRepo.save(user);

        return "Created " + roleName + " account: " + user.getUsername();
    }

    public List<UserEntity> listUsers() {
        return userRepo.findAll();
    }

    public boolean deleteUser(Long id) {
        if (userRepo.findById(id).isEmpty()) {
            return false;
        }
        userRepo.deleteById(id);
        return true;
    }
}