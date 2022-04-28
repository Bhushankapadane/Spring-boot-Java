package com.assignment.userservice.service;

import java.util.List;

import com.assignment.userservice.exception.UserAlreadyExistsException;
import com.assignment.userservice.exception.UserNotValidException;
import com.assignment.userservice.model.User;

public interface UserService {

	public User registerUser(User user) throws UserAlreadyExistsException;
	public User validateUser(User user) throws UserNotValidException;
	
	public List<User> getAllUsers();

}
