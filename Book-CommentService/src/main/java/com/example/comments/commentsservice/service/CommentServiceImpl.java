package com.example.comments.commentsservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.comments.commentsservice.model.Comment;
import com.example.comments.commentsservice.repository.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	CommentRepository fr;
	
	@Override
	public Comment savecomment(Comment c) {
		
		return fr.save(c);
	}
	
	@Override
	public List<Comment> getAllCommentByBookId(String bookId) {
	
				List<Comment> o=fr.findByBookId(bookId);
				
				if(!o.isEmpty())
				{
					return o;
				}
				return null;
	}
	@Override
	public void deleteComment(String commentId) {
		try {
		
			 fr.deleteByCommentId(commentId);
			
		}
		catch(Exception e)
		{
			System.out.println("Error deleting Comment by id");
		}
	
	}

}
