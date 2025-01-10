package Finance.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.util.Date;
import lombok.Data;

@Entity
@Data
public class Transaction {
  @Id
  private Long id;

  @ManyToOne
  private User user;

  private Double amount;
  private Date date;
  private String category;
  private String description;
}
