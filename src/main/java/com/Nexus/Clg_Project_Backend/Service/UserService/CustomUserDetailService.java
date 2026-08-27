package com.Nexus.Clg_Project_Backend.Service.UserService;

import com.Nexus.Clg_Project_Backend.Model.UserModel.CustomUserDetails;
import com.Nexus.Clg_Project_Backend.Model.UserModel.UserEntity;
import com.Nexus.Clg_Project_Backend.Repo.UserRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        UserEntity user = userRepo.findByUsername(username)
                            .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        return new CustomUserDetails(user);
    }
}
