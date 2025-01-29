package com.example.demo.model;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class MonthlyTotalDTO {
  private String month;
  private BigDecimal income;
  private BigDecimal expenses;
  private BigDecimal savings;
}
