package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private UserService userService;

  private User user;

  @BeforeEach
  public void setUp() {
    user = new User();
    user.setId(1L);
    user.setName("John Doe");
    user.setEmail("johndoe@example.com");
    user.setPassword("password123");
  }

  @Test
  public void testRegisterUser_Success() {
    when(userRepository.existsByEmail(user.getEmail())).thenReturn(false);
    when(userRepository.save(user)).thenReturn(user);
    User result = userService.registerUser(user);

    assertNotNull(result);
    assertEquals("John Doe", result.getName());
    assertEquals("johndoe@example.com", result.getEmail());
    verify(userRepository, times(1)).existsByEmail(user.getEmail());
    verify(userRepository, times(1)).save(user);
  }

  @Test
  public void testRegisterUser_EmailAlreadyExists() {
    when(userRepository.existsByEmail(user.getEmail())).thenReturn(true);
    RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
      userService.registerUser(user);
    });

    assertEquals("Email already exists", thrown.getMessage());
    verify(userRepository, times(1)).existsByEmail(user.getEmail());
    verify(userRepository, never()).save(user);
  }

  @Test
  public void testGetUserById_Success() {
    when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));
    User result = userService.getUserById(1L);

    assertNotNull(result);
    assertEquals("John Doe", result.getName());
    assertEquals("johndoe@example.com", result.getEmail());
    verify(userRepository, times(1)).findById(1L);
  }

  @Test
  public void testGetUserById_UserNotFound() {
    when(userRepository.findById(1L)).thenReturn(java.util.Optional.empty());

    RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
      userService.getUserById(1L);
    });

    assertEquals("User not found", thrown.getMessage());
    verify(userRepository, times(1)).findById(1L);
  }
}
