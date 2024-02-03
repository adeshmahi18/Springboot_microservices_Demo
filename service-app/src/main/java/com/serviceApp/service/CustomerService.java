package com.serviceApp.service;

import com.serviceApp.dto.AccountNumberDto;
import com.serviceApp.entity.AccountDetails;
import com.serviceApp.entity.Customer;

import java.util.List;

public interface CustomerService {
    public Customer createCustomer(Customer customer);
 }
