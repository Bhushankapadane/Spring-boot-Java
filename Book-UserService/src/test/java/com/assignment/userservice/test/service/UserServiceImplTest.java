package com.assignment.userservice.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.assignment.userservice.exception.UserAlreadyExistsException;
import com.assignment.userservice.exception.UserNotValidException;
import com.assignment.userservice.model.User;
import com.assignment.userservice.repository.UserRepository;
import com.assignment.userservice.service.UserServiceImpl;


@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
	
	@Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;
    
    User user;
    
    Optional<User> options;
    
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setUserName("john");
        user.setPassword("johnpass");
        user.setFirstName("John");
        user.setLastName("Kumar");
        user.setEmailId("john@gmail.com");
        user.setMobileNumber("1234567789");
        options = Optional.of(user);
    }
    
    @Test
    public void registerUserSuccess() throws UserAlreadyExistsException {
        when(userRepository.insert(user)).thenReturn(user);
        User userSaved = userService.registerUser(user);
        assertEquals(user, userSaved);
    }

    @Test
    public void registerUserFailure() throws UserAlreadyExistsException {
        when(userRepository.insert(user)).thenReturn(null);
        assertThrows(UserAlreadyExistsException.class,()->{userService.registerUser(user);});
    }
    
    @Test
	public void testValidateUserSuccess() throws UserNotValidException {
    	when(userRepository.findById(user.getUserName())).thenReturn(options);
		User status = userService.validateUser(user);
		assertEquals(user, status);
	}

	@Test
	public void testValidateUserFailure() throws UserNotValidException {
		assertThrows(UserNotValidException.class,()->{userService.validateUser(user);});
	}


}
