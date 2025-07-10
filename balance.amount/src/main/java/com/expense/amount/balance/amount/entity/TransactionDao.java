package com.expense.amount.balance.amount.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDao {
    private LocalDateTime dateTime;
    private String transactionId;
    private String transferred;
    private  Double withdrawals;
    private Double deposits;
    private Double balance;
}
