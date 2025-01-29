package com.example.demo.controller;

import com.example.demo.model.Transaction;
import com.example.demo.service.TransactionService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
  private final TransactionService transactionService;

  public TransactionController(TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  @PostMapping
  public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction transaction) {
    return ResponseEntity.ok(transactionService.addTransaction(transaction));
  }

  @GetMapping("/monthly/{userId}")
  public ResponseEntity<List<Transaction>> getTransactions(@PathVariable Long userId){
    return ResponseEntity.ok(transactionService.getTransactions(userId));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
    transactionService.deleteTransaction(id);
    return ResponseEntity.ok().build();
  }
}
