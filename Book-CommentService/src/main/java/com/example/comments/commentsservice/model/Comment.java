package com.example.comments.commentsservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection  = "comment")
public class Comment {
	
	
	
	public Comment(String bookId, String commentId, String usercomment, String username) {
		super();
		this.bookId = bookId;
		this.commentId = commentId;
		this.usercomment = usercomment;
		this.username = username;
	}
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public String getCommentId() {
		return commentId;
	}
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	public String getUsercomment() {
		return usercomment;
	}
	public void setUsercomment(String usercomment) {
		this.usercomment = usercomment;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	private String bookId;
	@Id
	private String commentId;
	private String usercomment;
	private String username;
}
