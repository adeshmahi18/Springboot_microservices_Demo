package com.serviceApp.service.impl;

import com.serviceApp.dto.LoginDto;
import com.serviceApp.dto.SignupDto;
import com.serviceApp.entity.User;
import com.serviceApp.enums.UserRole;
import com.serviceApp.repo.UserRepository;
import com.serviceApp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public SignupDto saveUser(SignupDto user) {
        User saveUser=modelMapper.map(user,User.class);
        saveUser.setRole(UserRole.ADMIN);
        saveUser.setPassword(passwordEncoder.encode(user.getPassword()));
         User savedUser=userRepository.save(saveUser);
        SignupDto savedDto=modelMapper.map(savedUser, SignupDto.class);
        return savedDto;
    }

    @Override
    public Optional<User> login(LoginDto loginDto) {
        String tempEmail=loginDto.getEmail();
        String tempPassword=loginDto.getPassword();
        System.out.println("email----"+tempEmail);
        Optional<User> user =userRepository.findByEmail(tempEmail);
        return user;
     }


}
