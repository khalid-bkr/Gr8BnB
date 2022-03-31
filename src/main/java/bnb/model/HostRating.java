package bnb.model;

/**
 * A Host Rating within the Gr8BnB application for CS5200
 * @author Kelly Song
 */
public class HostRating {
  protected int id;
  protected Host host;
  protected double rating;


  public HostRating(int id, Host host, double rating) {
    this.id = id;
    this.host = host;
    this.rating = rating;
  }
  
  public HostRating(int id) {
	  this.id = id;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Host getHost() {
    return host;
  }

  public void setHost(Host host) {
    this.host = host;
  }

  public double getRating() {
    return rating;
  }

  public void setRating(double rating) {
    this.rating = rating;
  }
}
