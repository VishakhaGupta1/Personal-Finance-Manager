package com.example.demo.controller;

import com.example.demo.model.SavingsGoal;
import com.example.demo.service.SavingsGoalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/savings-goals")
public class SavingsGoalController {
  private final SavingsGoalService savingsGoalService;

  public SavingsGoalController(SavingsGoalService savingsGoalService) {
    this.savingsGoalService = savingsGoalService;
  }

  @PostMapping
  public ResponseEntity<SavingsGoal> createSavingsGoal(@RequestBody SavingsGoal goal) {
    return ResponseEntity.ok(savingsGoalService.createSavingsGoal(goal));
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<List<SavingsGoal>> getUserSavingsGoals(@PathVariable Long userId) {
    return ResponseEntity.ok(savingsGoalService.getUserSavingsGoals(userId));
  }

  @PutMapping
  public ResponseEntity<SavingsGoal> updateSavingsGoal(@RequestBody SavingsGoal goal) {
    return ResponseEntity.ok(savingsGoalService.updateSavingsGoal(goal));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteSavingsGoal(@PathVariable Long id) {
    savingsGoalService.deleteSavingsGoal(id);
    return ResponseEntity.ok().build();
  }
}