package Finance.demo.controller;

import Finance.demo.models.Transaction;
import Finance.demo.models.User;
import Finance.demo.service.ReportService;
import Finance.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportController {

  @Autowired
  private ReportService reportService;

  @Autowired
  private TransactionService transactionService;

  @GetMapping("/income")
  public double getIncomeReport(@RequestParam Long userId) {
    User user = new User();
    user.setId(userId);
    List<Transaction> transactions = transactionService.getTransactionsByUser(user);
    return reportService.calculateTotalIncome(transactions);
  }

  @GetMapping("/expenses")
  public double getExpenseReport(@RequestParam Long userId) {
    User user = new User();
    user.setId(userId);
    List<Transaction> transactions = transactionService.getTransactionsByUser(user);
    return reportService.calculateTotalExpenses(transactions);
  }
}
