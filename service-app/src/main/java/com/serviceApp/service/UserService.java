package com.serviceApp.service;

import com.serviceApp.dto.LoginDto;
import com.serviceApp.dto.SignupDto;
import com.serviceApp.entity.Customer;
import com.serviceApp.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService {

    SignupDto saveUser(SignupDto user);

    Optional<User> login(LoginDto loginDto);

    UserDetailsService userDetailsService();

    interface CustomerService {
        public void createCustomer(Customer customer);
    }
}
