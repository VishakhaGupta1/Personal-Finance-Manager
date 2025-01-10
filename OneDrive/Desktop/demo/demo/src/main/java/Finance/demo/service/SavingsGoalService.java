package Finance.demo.service;

import Finance.demo.models.SavingsGoal;
import Finance.demo.models.User;
import Finance.demo.repositories.SavingsGoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SavingsGoalService {
  @Autowired
  private SavingsGoalRepository savingsGoalRepository;

  public SavingsGoal setSavingsGoal(SavingsGoal goal) {
    return savingsGoalRepository.save(goal);
  }

  public SavingsGoal getSavingsGoal(User user) {
    return savingsGoalRepository.findByUser(user);
  }
}
