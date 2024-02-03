package com.savings.savings.repo;

import com.savings.savings.entity.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionHistoryRepo extends JpaRepository<TransactionHistory, Long> {
}
