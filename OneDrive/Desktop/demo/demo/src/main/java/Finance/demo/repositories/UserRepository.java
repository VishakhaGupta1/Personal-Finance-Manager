package Finance.demo.repositories;


import Finance.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  User findByEmailAndPassword(String email, String password);
}
