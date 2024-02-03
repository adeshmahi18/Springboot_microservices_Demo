package com.serviceApp.repo;

import com.serviceApp.dto.AccountNumberDto;
import com.serviceApp.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepo extends JpaRepository<Customer, Long> {

    @Query("SELECT new com.serviceApp.dto.AccountNumberDto(c.name, ad.customerId, ad.accountNumber) FROM Customer c JOIN AccountDetails ad ON c.id = ad.customerId")
    List<AccountNumberDto> findAllCustomersAndAccountDetailsNativeQuery();
}
