package Finance.demo.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;


@Entity
@Data
public class User {
  @Id
  private Long id;

  @NotEmpty(message = "Name cannot be empty")
  private String name;

  @Email
  @NotEmpty(message = "Email cannot be empty")
  private String email;

  @NotEmpty(message = "Password cannot be empty")
  private String password;

  // Getters and setters
}
