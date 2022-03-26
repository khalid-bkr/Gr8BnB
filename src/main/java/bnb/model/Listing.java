package bnb.model;

import java.util.Date;

public class Listing {

  protected int id;
  protected String listingURL;
  protected String name;
  protected String description;
  protected String neighborhoodOverview;
  protected String  pictureUrl;
  protected Host host;
  protected Neighborhood  neighborhood;
  protected int accommodates;
  protected String  bathroomsText;
  protected int bedrooms;
  protected double price;
  protected boolean hasAvailability;
  protected int numberOfReviews;
  protected Date firstReview;
  protected Date lastReview;
  protected String  license;
  protected boolean instantBookable;
  protected double latitude;
  protected double longitude;
  protected RoomType roomType;
  protected String  propertyType;


  // Define RoomType like this so that the enum type can have white spaces
  public enum RoomType {
    ENTIRE("Entire home/apt"),
    PRIVATE("Private room"),
    SHARED("Shared room"),
    HOTEL("Hotel room");

    protected final String room;

    RoomType(String room) { this.room = room; }

    public String getRoom() { return this.room; }

    @Override public String toString() { return room; }

    public static RoomType fromString(String text) {
      for (RoomType type : RoomType.values()) {
        if (type.room.equalsIgnoreCase(text)) return type;
      }
      return null;
    }

  }


  public Listing(int id, String listingURL, String name, String description,
      String neighborhoodOverview, String pictureUrl, Host host, Neighborhood neighborhood,
      int accommodates, String bathroomsText, int bedrooms, double price, boolean hasAvailability,
      int numberOfReviews, Date firstReview, Date lastReview, String license,
      boolean instantBookable,
      double latitude, double longitude, RoomType roomType, String propertyType) {
    this.id = id;
    this.listingURL = listingURL;
    this.name = name;
    this.description = description;
    this.neighborhoodOverview = neighborhoodOverview;
    this.pictureUrl = pictureUrl;
    this.host = host;
    this.neighborhood = neighborhood;
    this.accommodates = accommodates;
    this.bathroomsText = bathroomsText;
    this.bedrooms = bedrooms;
    this.price = price;
    this.hasAvailability = hasAvailability;
    this.numberOfReviews = numberOfReviews;
    this.firstReview = firstReview;
    this.lastReview = lastReview;
    this.license = license;
    this.instantBookable = instantBookable;
    this.latitude = latitude;
    this.longitude = longitude;
    this.roomType = roomType;
    this.propertyType = propertyType;
  }


  public Listing(String listingURL, String name, String description,
      String neighborhoodOverview, String pictureUrl, Host host, Neighborhood neighborhood,
      int accommodates, String bathroomsText, int bedrooms, double price, boolean hasAvailability,
      int numberOfReviews, Date firstReview, Date lastReview, String license,
      boolean instantBookable,
      double latitude, double longitude, RoomType roomType, String propertyType) {
    this.listingURL = listingURL;
    this.name = name;
    this.description = description;
    this.neighborhoodOverview = neighborhoodOverview;
    this.pictureUrl = pictureUrl;
    this.host = host;
    this.neighborhood = neighborhood;
    this.accommodates = accommodates;
    this.bathroomsText = bathroomsText;
    this.bedrooms = bedrooms;
    this.price = price;
    this.hasAvailability = hasAvailability;
    this.numberOfReviews = numberOfReviews;
    this.firstReview = firstReview;
    this.lastReview = lastReview;
    this.license = license;
    this.instantBookable = instantBookable;
    this.latitude = latitude;
    this.longitude = longitude;
    this.roomType = roomType;
    this.propertyType = propertyType;
  }


  public int getID() {
    return id;
  }

  public void setID(int id) {
    this.id = id;
  }

  public String getListingURL() {
    return listingURL;
  }

  public void setListingURL(String listingURL) {
    this.listingURL = listingURL;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getNeighborhoodOverview() {
    return neighborhoodOverview;
  }

  public void setNeighborhoodOverview(String neighborhoodOverview) {
    this.neighborhoodOverview = neighborhoodOverview;
  }

  public String getPictureUrl() {
    return pictureUrl;
  }

  public void setPictureUrl(String pictureUrl) {
    this.pictureUrl = pictureUrl;
  }

  public Host getHost() {
    return host;
  }

  public void setHost(Host host) {
    this.host = host;
  }

  public Neighborhood getNeighborhood() {
    return neighborhood;
  }

  public void setNeighborhood(Neighborhood neighborhood) {
    this.neighborhood = neighborhood;
  }

  public int getAccommodates() {
    return accommodates;
  }

  public void setAccommodates(int accommodates) {
    this.accommodates = accommodates;
  }

  public String getBathroomsText() {
    return bathroomsText;
  }

  public void setBathroomsText(String bathroomsText) {
    this.bathroomsText = bathroomsText;
  }

  public int getBedrooms() {
    return bedrooms;
  }

  public void setBedrooms(int bedrooms) {
    this.bedrooms = bedrooms;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public boolean isHasAvailability() {
    return hasAvailability;
  }

  public void setHasAvailability(boolean hasAvailability) {
    this.hasAvailability = hasAvailability;
  }

  public int getNumberOfReviews() {
    return numberOfReviews;
  }

  public void setNumberOfReviews(int numberOfReviews) {
    this.numberOfReviews = numberOfReviews;
  }

  public Date getFirstReview() {
    return firstReview;
  }

  public void setFirstReview(Date firstReview) {
    this.firstReview = firstReview;
  }

  public Date getLastReview() {
    return lastReview;
  }

  public void setLastReview(Date lastReview) {
    this.lastReview = lastReview;
  }

  public String getLicense() {
    return license;
  }

  public void setLicense(String license) {
    this.license = license;
  }

  public boolean isInstantBookable() {
    return instantBookable;
  }

  public void setInstantBookable(boolean instantBookable) {
    this.instantBookable = instantBookable;
  }

  public double getLatitude() {
    return latitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  public RoomType getRoomType() {
    return roomType;
  }

  public void setRoomType(RoomType roomType) {
    this.roomType = roomType;
  }

  public String getPropertyType() {
    return propertyType;
  }

  public void setPropertyType(String propertyType) {
    this.propertyType = propertyType;
  }
}
