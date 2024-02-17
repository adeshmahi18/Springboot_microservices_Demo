package com.serviceApp.service.impl;

import com.serviceApp.entity.User;
import com.serviceApp.repo.UserRepository;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("email===="+email);
        Optional<User> authUser=userRepository.findByEmail(email);
        if(authUser.isEmpty())
            throw new UsernameNotFoundException("Invalid username");
        return new org.springframework.security.core.userdetails.User(authUser.get().getEmail(),authUser.get().getPassword(), new ArrayList<>());
    }
}
