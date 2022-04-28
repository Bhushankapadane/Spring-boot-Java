package com.book.favouriteBook.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.book.favouriteBook.model.Favourite;


@CrossOrigin(origins = "*")
public interface FavouriteRepository extends MongoRepository<Favourite, String>{

	

	
	public List<Favourite> findByUsername(String username);

	public Favourite deleteByBookIdAndUsername(String id,String username);
	
	public List<Favourite> findByBookIdAndUsername(String id,String username);
	
}
