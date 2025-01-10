package Finance.demo.service;

import Finance.demo.models.User;
import Finance.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;

  public User registerUser(User user) {
    return userRepository.save(user);
  }

  public User loginUser(String email, String password) {
    return userRepository.findByEmailAndPassword(email, password);
  }
}
