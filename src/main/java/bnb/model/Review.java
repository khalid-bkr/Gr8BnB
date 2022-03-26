package bnb.model;

import java.util.Date;

public class Review {
	protected Long id;
	protected Date date;
	protected Integer reviewerId;
	protected String comments;
	protected Integer listingId;
	
	public Review(Long id, Date date, Integer reviewerId, String comments, Integer listingId) {
		this.id = id;
		this.date = date;
		this.reviewerId = reviewerId;
		this.comments = comments;
		this.listingId = listingId;
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
	
	public Integer getReviewerId() {
		return reviewerId;
	}

	public void setReviewerId(Integer reviewerId) {
		this.reviewerId = reviewerId;
	}
	
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public Integer getListingId() {
		return listingId;
	}

	public void setListingId(Integer listingId) {
		this.listingId = listingId;
	}
}
