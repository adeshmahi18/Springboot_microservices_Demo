package com.serviceApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class SignupDto {
    private Long id;
    private String name;
    private String email;
     private String password;
    private String role;
}
