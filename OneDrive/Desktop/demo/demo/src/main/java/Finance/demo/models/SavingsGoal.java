package Finance.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.util.Date;
import lombok.Data;

@Entity
@Data
public class SavingsGoal {
  @Id
  private Long id;

  private Double targetAmount;
  private Date targetDate;

  @ManyToOne
  private User user;

}
