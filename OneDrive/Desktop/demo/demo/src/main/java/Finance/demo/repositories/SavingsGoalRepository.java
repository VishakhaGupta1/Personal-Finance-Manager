package Finance.demo.repositories;


import Finance.demo.models.SavingsGoal;
import Finance.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavingsGoalRepository extends JpaRepository<SavingsGoal, Long> {
  SavingsGoal findByUser(User user);
}
