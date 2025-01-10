package Finance.demo.service;

import Finance.demo.models.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

  public double calculateTotalExpenses(List<Transaction> transactions) {
    return transactions.stream()
        .filter(t -> t.getAmount() < 0)
        .mapToDouble(Transaction::getAmount)
        .sum();
  }

  public double calculateTotalIncome(List<Transaction> transactions) {
    return transactions.stream()
        .filter(t -> t.getAmount() > 0)
        .mapToDouble(Transaction::getAmount)
        .sum();
  }
}
