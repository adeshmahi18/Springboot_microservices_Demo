package com.savings.savings.service;

import com.savings.savings.dto.DebitTransactionDto;
import com.savings.savings.entity.Transaction;

public interface TransactionAmount {

    public Transaction transaction(Transaction transaction);
    public String debitTransaction(DebitTransactionDto debitTransactionDto);
}
