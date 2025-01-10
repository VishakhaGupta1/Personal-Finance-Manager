package Finance.demo.service;

import Finance.demo.models.Transaction;
import Finance.demo.models.User;
import Finance.demo.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
  @Autowired
  private TransactionRepository transactionRepository;

  public Transaction addTransaction(Transaction transaction) {
    return transactionRepository.save(transaction);
  }

  public List<Transaction> getTransactionsByUser(User user) {
    return transactionRepository.findByUser(user);
  }

  public Transaction updateTransaction(Transaction transaction) {
    return transactionRepository.save(transaction);
  }

  public void deleteTransaction(Long id) {
    transactionRepository.deleteById(id);
  }
}
