package com.savings.savings.controller;

import com.savings.savings.dto.DebitTransactionDto;
import com.savings.savings.entity.Transaction;
import com.savings.savings.repo.TransactionRepo;
import com.savings.savings.service.TransactionAmount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {
    @Autowired
    private TransactionAmount transactionAmount;
    @Autowired
    private TransactionRepo transactionRepo;

    @PostMapping("/transaction")
    public ResponseEntity<?> transaction(@RequestBody Transaction transaction){
        Transaction savedTransaction= transactionAmount.transaction(transaction);
        return new ResponseEntity<>(savedTransaction, HttpStatus.OK);
    }

    @PostMapping("/debitTransaction")
    public ResponseEntity<?> debitTransaction(@RequestBody DebitTransactionDto debitTransactionDto){
         String message =transactionAmount.debitTransaction(debitTransactionDto);
        System.out.println("Transactionmessage===="+message);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
