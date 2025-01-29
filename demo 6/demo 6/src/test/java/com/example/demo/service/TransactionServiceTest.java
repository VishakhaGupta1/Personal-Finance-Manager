package com.example.demo.service;

import com.example.demo.model.Transaction;
import com.example.demo.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

  @Mock
  private TransactionRepository transactionRepository;

  @InjectMocks
  private TransactionService transactionService;

  private Transaction transaction;

  @BeforeEach
  public void setUp() {
    transaction = new Transaction();
    transaction.setId(1L);
    transaction.setAmount(new BigDecimal("100.00"));
    transaction.setDate(LocalDate.now());
    transaction.setDescription("Test transaction");
    transaction.setType(Transaction.TransactionType.EXPENSE);
  }

  @Test
  public void testAddTransaction() {
    when(transactionRepository.save(transaction)).thenReturn(transaction);

    Transaction result = transactionService.addTransaction(transaction);

    assertNotNull(result);
    assertEquals("Test transaction", result.getDescription());
    verify(transactionRepository, times(1)).save(transaction);
  }

  @Test
  public void testGetTransactions() {
    when(transactionRepository.findByUserId(1L)).thenReturn(List.of(transaction));

    List<Transaction> result = transactionService.getTransactions(1L);

    assertNotNull(result);
    assertEquals(1, result.size());
    assertEquals("Test transaction", result.get(0).getDescription());
    verify(transactionRepository, times(1)).findByUserId(1L);
  }

  @Test
  public void testDeleteTransaction() {
    when(transactionRepository.existsById(1L)).thenReturn(true);

    transactionService.deleteTransaction(1L);

    verify(transactionRepository, times(1)).deleteById(1L);
  }

  @Test
  public void testDeleteTransaction_NotFound() {
    when(transactionRepository.existsById(1L)).thenReturn(false);

    RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
      transactionService.deleteTransaction(1L);
    });

    assertEquals("Transaction not found with id: 1", thrown.getMessage());
    verify(transactionRepository, never()).deleteById(any());
  }
}
