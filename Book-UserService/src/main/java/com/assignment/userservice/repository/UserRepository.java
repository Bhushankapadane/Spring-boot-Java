package com.assignment.userservice.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.assignment.userservice.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

	User findByUserName(String userName);

}
