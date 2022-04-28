package com.book.favouriteBook.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.favouriteBook.exception.FavouriteAlreadyExistsException;
import com.book.favouriteBook.model.Favourite;
import com.book.favouriteBook.repository.FavouriteRepository;



@Service
public class FavouriteServiceImpl implements FavouriteService {
	private static Logger logger=LoggerFactory.getLogger(FavouriteServiceImpl.class);
	@Autowired
	private FavouriteRepository favRepo;
	
	@Override
	public Favourite saveFavourite(Favourite fav) throws FavouriteAlreadyExistsException {
		List<Favourite> recExist=favRepo.findByBookIdAndUsername(fav.getBookId(), fav.getUsername());
		if(recExist!=null && !recExist.isEmpty()) {
			throw new FavouriteAlreadyExistsException("Already added to Favourites");
		}
		else {
			favRepo.save(fav);
			return fav;
		}
	
	}
	@Override
	public List<Favourite> getAllFavourite(String username) {
		
		List<Favourite> o=favRepo.findByUsername(username);
		
		if(!o.isEmpty())
		{
			return o;
		}
		return null;
		
	}
	@Override
	public Favourite deleteFromFavourite(String id, String username) {
		
		Favourite fa = null;
		try {
		
			 fa =favRepo.deleteByBookIdAndUsername(id,username);
			
		}
		catch(Exception e)
		{
			logger.error("Error while deleting from favourites");
		}
		
		return fa;
	}
	@Override
	public List<Favourite> getFavouriteBooksByIdAndUsername(String id, String username) {
		
		List<Favourite> o=favRepo.findByBookIdAndUsername(id, username);
		if(!o.isEmpty())
		{
			return o;
		}
		return null;
	}
}
