package com.example.demo.service;

import com.example.demo.model.Transaction;
import com.example.demo.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TransactionService {
  private final TransactionRepository transactionRepository;

  public TransactionService(TransactionRepository transactionRepository) {
    this.transactionRepository = transactionRepository;
  }

  public Transaction addTransaction(Transaction transaction) {
    return transactionRepository.save(transaction);
  }

  public List<Transaction> getTransactions(Long userId) {
    return transactionRepository.findByUserId(userId);
  }

  public void deleteTransaction(Long id) {
    transactionRepository.deleteById(id);
  }
}
