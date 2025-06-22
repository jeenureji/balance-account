package com.expense.amount.balance.amount.repository;

import com.expense.amount.balance.amount.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
}
