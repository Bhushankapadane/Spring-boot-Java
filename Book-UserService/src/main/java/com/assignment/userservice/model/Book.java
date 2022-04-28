package com.assignment.userservice.model;

import lombok.Data;

@Data
public class Book {
	
	private String kind;
	private String id;
	private String etag;
	private VolumeInfo volumeInfo;
	
	
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Book(String kind, String id, String etag, VolumeInfo volumeInfo) {
		super();
		this.kind = kind;
		this.id = id;
		this.etag = etag;
		this.volumeInfo = volumeInfo;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEtag() {
		return etag;
	}
	public void setEtag(String etag) {
		this.etag = etag;
	}
	public VolumeInfo getVolumeInfo() {
		return volumeInfo;
	}
	public void setVolumeInfo(VolumeInfo volumeInfo) {
		this.volumeInfo = volumeInfo;
	}
	
	
	
}
