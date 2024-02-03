package com.serviceApp.controller;

import com.serviceApp.dto.AccountNumberDto;
import com.serviceApp.entity.AccountDetails;
import com.serviceApp.entity.Customer;
import com.serviceApp.repo.AccountRepo;
import com.serviceApp.repo.CustomerRepo;
import com.serviceApp.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

@RestController
public class CustomerController {


    @Autowired
    private CustomerService customerService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @PostMapping("/createCustomer")
    public String createCustomer(@RequestBody Customer customer){
        Customer  savedCustomer = customerService.createCustomer(customer);
       Long customerId= savedCustomer.getId();
       AccountDetails account= accountRepo.findByCustomerId(customerId);
        return account.getAccountNumber();
    }

    /*@GetMapping("/findAllByEntity")
    public List<AccountDetails>  getAllAccoasr(){
        return accountRepo.findAll();
    }*/

    @GetMapping("/findAllAccount")
    public List<AccountNumberDto>  getAllAccountNumber(){
        return customerRepo.findAllCustomersAndAccountDetailsNativeQuery();
     }

    @GetMapping("/findAllForTest")
    public Integer  findAllCustForTest(){
         List<String> mobile=customerRepo.findAll().stream().map(cust->cust.getMobile()).sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        System.out.println(mobile);
          int minNumber = mobile.stream().mapToInt(Integer::parseInt).summaryStatistics().getMax();
        System.out.println(minNumber);
        return minNumber;
     }


}
