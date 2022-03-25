package bnb.model;

public class ListingRating {
  public enum ScoreType {
    Rating, Accuracy, Cleanliness, Checkin, Communication, Location, Value
  }

  protected int id;
  protected int listingId;
  protected int hostId;
  protected ScoreType scoreType;
  protected double score;

  public ListingRating(int id, int listingId, int hostId, ScoreType scoreType, double score) {
    this.id = id;
    this.listingId = listingId;
    this.hostId = hostId;
    this.scoreType = scoreType;
    this.score = score;
  }

  public ListingRating(int listingId, int hostId, ScoreType scoreType, double score) {
    this.listingId = listingId;
    this.hostId = hostId;
    this.scoreType = scoreType;
    this.score = score;
  }

  public ListingRating(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getListingId() {
    return listingId;
  }

  public void setListingId(int listingId) {
    this.listingId = listingId;
  }

  public int getHostId() {
    return hostId;
  }

  public void setHostId(int hostId) {
    this.hostId = hostId;
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
    return "ListRating{" +
      "id=" + id +
      ", listingId=" + listingId +
      ", hostId=" + hostId +
      ", scoreType=" + scoreType +
      ", score=" + score +
      '}';
  }
}
