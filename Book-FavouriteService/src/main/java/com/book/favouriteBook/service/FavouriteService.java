package com.book.favouriteBook.service;

import java.util.List;

import com.book.favouriteBook.exception.FavouriteAlreadyExistsException;
import com.book.favouriteBook.model.Favourite;


public interface FavouriteService {

	public Favourite saveFavourite(Favourite f) throws FavouriteAlreadyExistsException;
	public List<Favourite> getAllFavourite (String username);
	public Favourite deleteFromFavourite(String id, String username);
	
	public List<Favourite> getFavouriteBooksByIdAndUsername(String id, String username);
}
