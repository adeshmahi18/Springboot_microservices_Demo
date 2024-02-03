package com.savings.savings.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionHistory {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
     private String accountNumber;
    private String status;
    private long depositAmount;
    private long withdrawAmount;
    private long totalAmount;

}
