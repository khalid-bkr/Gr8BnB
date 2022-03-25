package bnb.model;

public class Amenity {
	protected int id;
	protected String title;
	
	public Amenity(int id, String title) {
		this.id = id;
		this.title = title;
	}

	public Amenity(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}
}
