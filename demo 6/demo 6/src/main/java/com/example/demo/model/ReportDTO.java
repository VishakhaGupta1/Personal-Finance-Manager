package com.example.demo.model;


import lombok.Data;
import java.math.BigDecimal;
import java.util.Map;
import java.util.List;

@Data
public class ReportDTO {
  private BigDecimal totalIncome;
  private BigDecimal totalExpenses;
  private BigDecimal totalSavings;
  private Map<String, BigDecimal> categoryWiseExpenses;
  private List<MonthlyTotalDTO> monthlyTotals;
  private Map<String, Double> savingsGoalProgress;
}

