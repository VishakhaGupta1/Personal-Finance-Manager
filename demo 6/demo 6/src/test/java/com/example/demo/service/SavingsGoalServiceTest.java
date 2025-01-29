package com.example.demo.service;

import com.example.demo.model.SavingsGoal;
import com.example.demo.repository.SavingsGoalRepository;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import java.util.List;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SavingsGoalServiceTest {

  @Mock
  private SavingsGoalRepository savingsGoalRepository;

  @InjectMocks
  private SavingsGoalService savingsGoalService;

  private SavingsGoal savingsGoal;

  @BeforeEach
  public void setUp() {
    savingsGoal = new SavingsGoal();
    savingsGoal.setId(1L);
    savingsGoal.setName("Vacation Fund");
    savingsGoal.setTargetAmount(new BigDecimal("5000.00"));
    savingsGoal.setCurrentAmount(new BigDecimal("1500.00"));
  }

  @Test
  public void testCreateSavingsGoal() {
    when(savingsGoalRepository.save(savingsGoal)).thenReturn(savingsGoal);

    SavingsGoal result = savingsGoalService.createSavingsGoal(savingsGoal);

    assertNotNull(result);
    assertEquals("Vacation Fund", result.getName());
    verify(savingsGoalRepository, times(1)).save(savingsGoal);
  }

  @Test
  public void testGetUserSavingsGoals() {
    when(savingsGoalRepository.findByUserId(1L)).thenReturn(List.of(savingsGoal));

    List<SavingsGoal> result = savingsGoalService.getUserSavingsGoals(1L);

    assertNotNull(result);
    assertEquals(1, result.size());
    assertEquals("Vacation Fund", result.get(0).getName());
    verify(savingsGoalRepository, times(1)).findByUserId(1L);
  }

  @Test
  public void testUpdateSavingsGoal() {
    when(savingsGoalRepository.save(savingsGoal)).thenReturn(savingsGoal);
    savingsGoal.setName("Updated Vacation Fund");

    SavingsGoal result = savingsGoalService.updateSavingsGoal(savingsGoal);

    assertNotNull(result);
    assertEquals("Updated Vacation Fund", result.getName());
    verify(savingsGoalRepository, times(1)).save(savingsGoal);
  }
}

