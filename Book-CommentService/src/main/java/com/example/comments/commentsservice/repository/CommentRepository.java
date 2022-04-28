package com.example.comments.commentsservice.repository;
import com.example.comments.commentsservice.model.Comment;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String>{
	
	public List<Comment> findByBookId(String bookId);
	public void deleteByCommentId(String commentId);

}
