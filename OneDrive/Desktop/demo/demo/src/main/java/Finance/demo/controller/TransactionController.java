package Finance.demo.controller;

import Finance.demo.models.Transaction;
import Finance.demo.models.User;
import Finance.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
  @Autowired
  private TransactionService transactionService;

  @PostMapping("/add")
  public Transaction addTransaction(@RequestBody Transaction transaction) {
    return transactionService.addTransaction(transaction);
  }

  @GetMapping("/user")
  public List<Transaction> getTransactionsByUser(@RequestParam Long userId) {
    User user = new User();
    user.setId(userId);
    return transactionService.getTransactionsByUser(user);
  }

  @PutMapping("/update")
  public Transaction updateTransaction(@RequestBody Transaction transaction) {
    return transactionService.updateTransaction(transaction);
  }

  @DeleteMapping("/delete")
  public void deleteTransaction(@RequestParam Long id) {
    transactionService.deleteTransaction(id);
  }
}
