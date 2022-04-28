package com.assignment.userservice.model;

import lombok.Data;

@Data
public class ImageLinks {
	private String smallThumbnail;
	private String thumbnail;
	
	
	
	public ImageLinks() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ImageLinks(String smallThumbnail, String thumbnail) {
		super();
		this.smallThumbnail = smallThumbnail;
		this.thumbnail = thumbnail;
	}
	public String getSmallThumbnail() {
		return smallThumbnail;
	}
	public void setSmallThumbnail(String smallThumbnail) {
		this.smallThumbnail = smallThumbnail;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	
	

}
