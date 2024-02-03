package com.serviceApp.repo;

import com.serviceApp.dto.AccountNumberDto;
import com.serviceApp.entity.AccountDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepo extends JpaRepository<AccountDetails, Long> {
    AccountDetails findByCustomerId(Long customerId);

    List<AccountDetails> findAllByStatus(String active);

}
