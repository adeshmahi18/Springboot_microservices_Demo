package com.serviceApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
 public class AccountNumberDto {
    private String name;
    private Long customerId;
    private String accountNumber;

    public AccountNumberDto(String name, Long customerId, String accountNumber) {
        this.name = name;
        this.customerId = customerId;
        this.accountNumber = accountNumber;
    }
 }
