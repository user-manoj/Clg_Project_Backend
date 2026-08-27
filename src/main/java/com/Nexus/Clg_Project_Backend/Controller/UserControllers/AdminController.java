package com.Nexus.Clg_Project_Backend.Controller.UserControllers;

import com.Nexus.Clg_Project_Backend.DTO.UserDTO.AdminCreateUserRequestDTO;
import com.Nexus.Clg_Project_Backend.Model.UserModel.UserEntity;
import com.Nexus.Clg_Project_Backend.Service.UserService.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
@CrossOrigin(origins = "http://localhost:5173")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody AdminCreateUserRequestDTO request) {
        String result = adminService.createUser(request);

        if (result.startsWith("Username already exists")) {
            return ResponseEntity.badRequest().body(result);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<UserEntity>> listUsers() {
        return ResponseEntity.ok(adminService.listUsers());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        boolean deleted = adminService.deleteUser(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("User deleted");
    }
}