package com.savings.savings.repo;

import com.savings.savings.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {
    Transaction findByAccountNumber(String accountNumber);
}
