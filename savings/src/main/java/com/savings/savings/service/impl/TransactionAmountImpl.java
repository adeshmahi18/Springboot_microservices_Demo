package com.savings.savings.service.impl;

import com.savings.savings.dto.DebitTransactionDto;
import com.savings.savings.entity.Transaction;
import com.savings.savings.entity.TransactionHistory;
import com.savings.savings.repo.TransactionHistoryRepo;
import com.savings.savings.repo.TransactionRepo;
import com.savings.savings.service.TransactionAmount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionAmountImpl implements TransactionAmount {
    @Autowired
    private TransactionRepo transactionRepo;
    @Autowired
    private TransactionHistoryRepo transactionHistoryRepo;
    @Override
    @Transactional
    public Transaction transaction(Transaction transaction) {
        transaction.setStatus("Cr");

             if (transaction.getStatus().equals("Cr")) {

                 Transaction tempTransaction = transactionRepo.findByAccountNumber(transaction.getAccountNumber());
                 long tempTotal=0;
                  if(tempTransaction==null){
                      tempTransaction = new Transaction();
                     tempTotal=0;

                      tempTransaction.setAmount(transaction.getAmount());
                       tempTransaction.setTotalAmount(transaction.getAmount());
                      tempTransaction.setStatus("Cr");
                      tempTransaction.setAccountNumber(transaction.getAccountNumber());
                      tempTransaction.setCustomerId(transaction.getCustomerId());

                   }else{
                     tempTotal = tempTransaction.getTotalAmount();

                      long tempDepositAmount = transaction.getAmount();
                      long total = tempTotal + tempDepositAmount;

                      tempTransaction.setAmount(transaction.getAmount());
                      tempTransaction.setTotalAmount(total);
                      tempTransaction.setStatus("Cr");
                      tempTransaction.setAccountNumber(tempTransaction.getAccountNumber());
                      tempTransaction.setCustomerId(tempTransaction.getCustomerId());
                 }

                 Transaction savedTransaction = transactionRepo.save(tempTransaction);

                  TransactionHistory transactionHistory = new TransactionHistory();
                 transactionHistory.setAccountNumber(transaction.getAccountNumber());
                 transactionHistory.setDepositAmount(transaction.getAmount());
                 transactionHistory.setWithdrawAmount(0);
                 transactionHistory.setStatus("Cr");
                 transactionHistory.setTotalAmount(tempTotal + transaction.getAmount());
                 transactionHistoryRepo.save(transactionHistory);
                 return savedTransaction;

             }
        return null;
    }

    @Override
    public String debitTransaction(DebitTransactionDto debitTransactionDto) {
        String accountNumber = debitTransactionDto.getAccountNumber();
        Transaction newTemptransaction=new Transaction();
        Transaction tempTransaction=transactionRepo.findByAccountNumber(accountNumber);
        Long tempTotalAmount = tempTransaction.getTotalAmount();
        Long tempDebitTransactionDto =debitTransactionDto.getAmount();
        if(tempTotalAmount < tempDebitTransactionDto){
            return "In sufficient funds";
        }else{
            return "Transaction done";
        }
     }

}
