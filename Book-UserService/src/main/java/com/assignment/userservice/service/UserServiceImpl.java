package com.assignment.userservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.userservice.exception.UserAlreadyExistsException;
import com.assignment.userservice.exception.UserNotValidException;
import com.assignment.userservice.model.User;
import com.assignment.userservice.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepo;
	
	public User registerUser(User user) throws UserAlreadyExistsException {
		User newUser = userRepo.insert(user);
		if(newUser!=null) {
			return newUser;
		}
		else {
			throw new UserAlreadyExistsException("User Already Exists");
		}
	}
	
	public User validateUser(User user) throws UserNotValidException { 
		Optional<User> result = userRepo.findById(user.getUserName());
		if(!result.isPresent()) { 
			throw new UserNotValidException("Invalid Username/Password");
		}
		if(!(result.get().getPassword().equals(user.getPassword()))) {
			throw new UserNotValidException("Invalid Username/Password");
		}
		return result.get();
	}
	
	
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}
	
}
