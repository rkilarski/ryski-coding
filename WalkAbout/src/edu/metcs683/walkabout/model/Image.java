package edu.metcs683.walkabout.model;

import java.io.Serializable;

public class Image implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private Image image;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	
}
