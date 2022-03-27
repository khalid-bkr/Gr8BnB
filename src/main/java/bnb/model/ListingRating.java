package bnb.model;

public class ListingRating {
	public enum ScoreType {
    Rating, Accuracy, Cleanliness, Checkin, Communication, Location, Value
  }

  protected int id;
  protected Listing listing;
  protected Host host;
  protected ScoreType scoreType;
  protected double score;
  
	public ListingRating(int id, Listing listing, Host host, ScoreType scoreType, double score) {
		this.id = id;
		this.listing = listing;
		this.host = host;
		this.scoreType = scoreType;
		this.score = score;
	}
	
	public ListingRating(Listing listing, Host host, ScoreType scoreType, double score) {
		this.listing = listing;
		this.host = host;
		this.scoreType = scoreType;
		this.score = score;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Listing getListing() {
		return listing;
	}

	public void setListing(Listing listing) {
		this.listing = listing;
	}

	public Host getHost() {
		return host;
	}

	public void setHost(Host host) {
		this.host = host;
	}

	public ScoreType getScoreType() {
		return scoreType;
	}

	public void setScoreType(ScoreType scoreType) {
		this.scoreType = scoreType;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "ListingRating [id=" + id + ", listing=" + listing + ", host=" + host + ", scoreType=" + scoreType
				+ ", score=" + score + "]";
	}
  

}
