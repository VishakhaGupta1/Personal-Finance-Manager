package Finance.demo.repositories;

import Finance.demo.models.Transaction;
import Finance.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
  List<Transaction> findByUser(User user);
}
