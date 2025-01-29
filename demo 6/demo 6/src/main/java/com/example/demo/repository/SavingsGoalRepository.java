package com.example.demo.repository;


import com.example.demo.model.SavingsGoal;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SavingsGoalRepository extends JpaRepository<SavingsGoal, Long> {
  List<SavingsGoal> findByUserId(Long userId);
}
