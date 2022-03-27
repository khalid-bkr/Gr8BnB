package bnb.model;

import java.util.Date;

public class Review {
	protected Long id;
	protected Date date;
	protected Guest guest;
	protected String comments;
	protected Listing listing;
	
	public Review(Long id, Date date, Guest guest, String comments, Listing listing) {
		this.id = id;
		this.date = date;
		this.guest = guest;
		this.comments = comments;
		this.listing = listing;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}
	
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public Listing getListing() {
		return listing;
	}

	public void setListing(Listing listing) {
		this.listing = listing;
	}
}
