package com.assignment.userservice.model;

import java.util.List;

import lombok.Data;

@Data
public class VolumeInfo {
	private String title;
	private String subtitle;
	private List<String> authors;
	private String publisher;
	private String publishedDate;
	private String Description;
	private int pageCount;
	private List<String> categories;
	private ImageLinks imageLinks;
	private String language;
	private String previewLink;
	
	
	public VolumeInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public VolumeInfo(String title, String subtitle, List<String> authors, String publisher, String publishedDate,
			String description, int pageCount, List<String> categories, ImageLinks imageLinks, String language,
			String previewLink) {
		super();
		this.title = title;
		this.subtitle = subtitle;
		this.authors = authors;
		this.publisher = publisher;
		this.publishedDate = publishedDate;
		Description = description;
		this.pageCount = pageCount;
		this.categories = categories;
		this.imageLinks = imageLinks;
		this.language = language;
		this.previewLink = previewLink;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	public List<String> getAuthors() {
		return authors;
	}
	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getPublishedDate() {
		return publishedDate;
	}
	public void setPublishedDate(String publishedDate) {
		this.publishedDate = publishedDate;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public List<String> getCategories() {
		return categories;
	}
	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
	public ImageLinks getImageLinks() {
		return imageLinks;
	}
	public void setImageLinks(ImageLinks imageLinks) {
		this.imageLinks = imageLinks;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getPreviewLink() {
		return previewLink;
	}
	public void setPreviewLink(String previewLink) {
		this.previewLink = previewLink;
	}
	
	
	

}
