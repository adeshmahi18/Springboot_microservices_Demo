package com.serviceApp.service.impl;

import com.serviceApp.dto.AccountNumberDto;
import com.serviceApp.entity.AccountDetails;
import com.serviceApp.entity.Customer;
import com.serviceApp.repo.AccountRepo;
import com.serviceApp.repo.CustomerRepo;
import com.serviceApp.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Override

    public Customer createCustomer(Customer customer) {
        Customer createdCustomer = customerRepo.save(customer);
        AccountDetails account=new AccountDetails();
        long eightDigitRandomNumber = generateEightDigitRandomNumber();
        String tempAccount= Long.toString(eightDigitRandomNumber);
        account.setCustomerId(createdCustomer.getId());
        account.setAccountNumber(tempAccount);
        account.setStatus("Active");
        AccountDetails savedAccount=accountRepo.save(account);
        return createdCustomer;
     }



    private static long generateEightDigitRandomNumber() {
        // Create an instance of the Random class
        Random random = new Random();

        // Generate a random number between 10000000 (inclusive) and 100000000 (exclusive)
        long eightDigitRandomNumber = 10000000 + random.nextInt(90000000);

        return eightDigitRandomNumber;
    }
}
