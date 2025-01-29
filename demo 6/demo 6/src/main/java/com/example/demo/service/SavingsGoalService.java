package com.example.demo.service;

import com.example.demo.model.SavingsGoal;
import com.example.demo.repository.SavingsGoalRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SavingsGoalService {
  private final SavingsGoalRepository savingsGoalRepository;

  public SavingsGoalService(SavingsGoalRepository savingsGoalRepository) {
    this.savingsGoalRepository = savingsGoalRepository;
  }


  public SavingsGoal createSavingsGoal(SavingsGoal goal) {
    return savingsGoalRepository.save(goal);
  }

  public List<SavingsGoal> getUserSavingsGoals(Long userId) {
    return savingsGoalRepository.findByUserId(userId);
  }

  public SavingsGoal updateSavingsGoal(SavingsGoal goal) {
    return savingsGoalRepository.save(goal);
  }

  public void deleteSavingsGoal(Long id) {
    savingsGoalRepository.deleteById(id);
  }
}
