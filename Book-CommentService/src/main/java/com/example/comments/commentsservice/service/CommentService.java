package com.example.comments.commentsservice.service;

import java.util.List;

import com.example.comments.commentsservice.model.Comment;

public interface CommentService {
	public List<Comment> getAllCommentByBookId (String bookId);
	public void deleteComment(String commentId);
	public Comment savecomment(Comment b);
}
