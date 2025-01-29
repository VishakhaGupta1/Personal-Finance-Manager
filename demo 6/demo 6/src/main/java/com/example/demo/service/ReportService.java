package com.example.demo.service;

import com.example.demo.model.ReportDTO;
import com.example.demo.model.MonthlyTotalDTO;
import com.example.demo.model.Transaction;
import com.example.demo.model.SavingsGoal;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.repository.SavingsGoalRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

@Service
public class ReportService {
  private final TransactionRepository transactionRepository;
  private final SavingsGoalRepository savingsGoalRepository;

  public ReportService(TransactionRepository transactionRepository,
                       SavingsGoalRepository savingsGoalRepository) {
    this.transactionRepository = transactionRepository;
    this.savingsGoalRepository = savingsGoalRepository;
  }

  public ReportDTO generateMonthlyReport(Long userId, LocalDate startDate, LocalDate endDate) {
    List<Transaction> transactions = transactionRepository.findByUserIdAndDateBetween(
        userId, startDate, endDate);
    List<SavingsGoal> savingsGoals = savingsGoalRepository.findByUserId(userId);

    ReportDTO report = new ReportDTO();

    BigDecimal totalIncome = calculateTotal(transactions, Transaction.TransactionType.INCOME);
    BigDecimal totalExpenses = calculateTotal(transactions, Transaction.TransactionType.EXPENSE);

    report.setTotalIncome(totalIncome);
    report.setTotalExpenses(totalExpenses);
    report.setTotalSavings(totalIncome.subtract(totalExpenses));

    Map<String, BigDecimal> categoryWiseExpenses = transactions.stream()
        .filter(t -> t.getType() == Transaction.TransactionType.EXPENSE)
        .collect(Collectors.groupingBy(
            t -> t.getCategory().getName(),
            Collectors.reducing(BigDecimal.ZERO, Transaction::getAmount, BigDecimal::add)
        ));
    report.setCategoryWiseExpenses(categoryWiseExpenses);

    List<MonthlyTotalDTO> monthlyTotals = calculateMonthlyTotals(transactions);
    report.setMonthlyTotals(monthlyTotals);

    Map<String, Double> savingsProgress = calculateSavingsProgress(savingsGoals);
    report.setSavingsGoalProgress(savingsProgress);

    return report;
  }

  private BigDecimal calculateTotal(List<Transaction> transactions, Transaction.TransactionType type) {
    return transactions.stream()
        .filter(t -> t.getType() == type)
        .map(Transaction::getAmount)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  private List<MonthlyTotalDTO> calculateMonthlyTotals(List<Transaction> transactions) {
    Map<String, MonthlyTotalDTO> monthlyTotals = new HashMap<>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");

    transactions.forEach(transaction -> {
      String monthKey = transaction.getDate().format(formatter);
      MonthlyTotalDTO monthTotal = monthlyTotals.computeIfAbsent(monthKey, k -> {
        MonthlyTotalDTO dto = new MonthlyTotalDTO();
        dto.setMonth(monthKey);
        dto.setIncome(BigDecimal.ZERO);
        dto.setExpenses(BigDecimal.ZERO);
        dto.setSavings(BigDecimal.ZERO);
        return dto;
      });

      if (transaction.getType() == Transaction.TransactionType.INCOME) {
        monthTotal.setIncome(monthTotal.getIncome().add(transaction.getAmount()));
      } else {
        monthTotal.setExpenses(monthTotal.getExpenses().add(transaction.getAmount()));
      }
      monthTotal.setSavings(monthTotal.getIncome().subtract(monthTotal.getExpenses()));
    });

    return new ArrayList<>(monthlyTotals.values());
  }

  private Map<String, Double> calculateSavingsProgress(List<SavingsGoal> savingsGoals) {
    return savingsGoals.stream()
        .collect(Collectors.toMap(
            SavingsGoal::getName,
            goal -> (goal.getCurrentAmount().doubleValue() / goal.getTargetAmount().doubleValue()) * 100
        ));
  }

}
