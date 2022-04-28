package com.assignment.userservice.model;

import java.util.List;

import lombok.Data;

@Data
public class BookList {
	
	private List<Book> items;
	
	

	public BookList() {
		super();
		// TODO Auto-generated constructor stub
		}
	

	public BookList(List<Book> items) {
		super();
		this.items = items;
	}


	public List<Book> getItems() {
		return items;
	}

	public void setItems(List<Book> items) {
		this.items = items;
	}
	
	
}
