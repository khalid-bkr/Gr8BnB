package bnb.dal;

import bnb.model.Host;
import bnb.model.Listing;
import bnb.model.Listing.RoomType;
import bnb.model.Neighborhood;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListingDao {

  protected ConnectionManager connectionManager;
  private static ListingDao instance = null;

  protected ListingDao() {
    this.connectionManager = new ConnectionManager();
  }

  public static ListingDao getInstance() {
    if (instance == null) {
      instance = new ListingDao();
    }
    return instance;
  }

  public Listing create(Listing listing) throws SQLException {
    String insertListing= "INSERT INTO Listing(ID, ListingUrl, Name, Description,"
        + " NeighborhoodOverview, PictureUrl, HostID, Neighborhood, Accommodates,"
        + " BathroomsText, Bedrooms, Price, HasAvailability, NumberOfReviews, FirstReview,"
        + " LastReview, License, InstantBookable, Latitude, Longitude, RoomType, PropertyType) "
        + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

    Connection connection = null;
    PreparedStatement insertStmt = null;
    ResultSet resultKey = null;

    try {
          connection = connectionManager.getConnection();
          insertStmt = connection.prepareStatement(insertListing);

          insertStmt.setInt(1, listing.getID());
          insertStmt.setString(2, listing.getListingURL());
          insertStmt.setString(3, listing.getDescription());
          insertStmt.setString(4, listing.getNeighborhoodOverview());
          insertStmt.setString(5, listing.getPictureUrl());
          insertStmt.setInt(6, listing.getHost().getId());
          insertStmt.setString(7, listing.getNeighborhood().getNeighborhood());
          insertStmt.setInt(8, listing.getAccommodates());
          insertStmt.setString(9, listing.getBathroomsText());
          insertStmt.setInt(10, listing.getBedrooms());
          insertStmt.setDouble(11, listing.getPrice());
          insertStmt.setBoolean(12, listing.isHasAvailability());
          insertStmt.setInt(13, listing.getNumberOfReviews());
          insertStmt.setTimestamp(14, new Timestamp(listing.getFirstReview().getTime()));
          insertStmt.setTimestamp(15, new Timestamp(listing.getLastReview().getTime()));
          insertStmt.setString(16, listing.getLicense());
          insertStmt.setBoolean(17, listing.isInstantBookable());
          insertStmt.setDouble(18, listing.getLatitude());
          insertStmt.setDouble(19, listing.getLongitude());
          insertStmt.setString(20, listing.getRoomType().getRoom());
          insertStmt.setString(21, listing.getPropertyType());

          insertStmt.executeUpdate();

          return listing;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (insertStmt != null) {
        insertStmt.close();
      }
    }
  }



  public Listing getListingById (int id) throws SQLException {
    String selectListing = "SELECT * FROM HostRating WHERE ID=?;";

    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;

    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectListing);
      HostDao hostDao = HostDao.getInstance();
      NeighborhoodDao neighborhoodDao = NeighborhoodDao.getInstance();

      selectStmt.setInt(1, id);

      results = selectStmt.executeQuery();

      if (results.next()) {
        int resultId = results.getInt("ID");
        String listingURL = results.getString("ListingUrl");
        String name = results.getString("Name");
        String description = results.getString("Description");
        String neighborhoodOverview = results.getString("NeighborhoodOverview");
        String  pictureUrl = results.getString("pictureUrl");
        Host host = hostDao.getHostById(results.getInt("HostID"));
        Neighborhood neighborhood =
            neighborhoodDao.getNeighborhoodFromNeighborhood(results.getString("Neighborhood"));
        int accommodates = results.getInt("Accommodates");
        String  bathroomsText = results.getString("BathroomsText");
        int bedrooms = results.getInt("Bedrooms");
        double price = results.getDouble("Price");
        boolean hasAvailability = results.getBoolean("hasAvailability");;
        int numberOfReviews = results.getInt("NumberOfReviews");
        Date firstReview = results.getDate("FirstReview");
        Date lastReview = results.getDate("LastReview");
        String  license = results.getString("License");
        boolean instantBookable = results.getBoolean("InstantBookable");;
        double latitude = results.getDouble("Latitude");
        double longitude = results.getDouble("Longitude");
        RoomType roomType = RoomType.fromString(results.getString("RoomType"));
        String  propertyType = results.getString("PropertyType");

        Listing listing = new Listing(resultId, listingURL, name, description, neighborhoodOverview,
            pictureUrl, host, neighborhood, accommodates,  bathroomsText, bedrooms, price, hasAvailability,
         numberOfReviews,  firstReview,  lastReview, license, instantBookable, latitude, longitude,
            roomType, propertyType);

        return listing;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(selectStmt != null) {
        selectStmt.close();
      }
      if(results != null) {
        results.close();
      }
    }
    return null;
  }



  public List<Listing> getListingByHostId(int hostId) throws SQLException {
    List<Listing> listings = new ArrayList<>();
    String selectListings = "SELECT * FROM Listing WHERE HostId=?;";

    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;

    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectListings);
      selectStmt.setInt(1, hostId);
      HostDao hostDao = HostDao.getInstance();
      NeighborhoodDao neighborhoodDao = NeighborhoodDao.getInstance();

      results = selectStmt.executeQuery();
      while (results.next()) {

        int resultId = results.getInt("ID");
        String listingURL = results.getString("ListingUrl");
        String name = results.getString("Name");
        String description = results.getString("Description");
        String neighborhoodOverview = results.getString("NeighborhoodOverview");
        String  pictureUrl = results.getString("pictureUrl");
        Host host = hostDao.getHostById(hostId);
        Neighborhood neighborhood =
            neighborhoodDao.getNeighborhoodFromNeighborhood(results.getString("Neighborhood"));
        int accommodates = results.getInt("Accommodates");
        String  bathroomsText = results.getString("BathroomsText");
        int bedrooms = results.getInt("Bedrooms");
        double price = results.getDouble("Price");
        boolean hasAvailability = results.getBoolean("hasAvailability");;
        int numberOfReviews = results.getInt("NumberOfReviews");
        Date firstReview = results.getDate("FirstReview");
        Date lastReview = results.getDate("LastReview");
        String  license = results.getString("License");
        boolean instantBookable = results.getBoolean("InstantBookable");;
        double latitude = results.getDouble("Latitude");
        double longitude = results.getDouble("Longitude");
        RoomType roomType = RoomType.fromString(results.getString("RoomType"));
        String  propertyType = results.getString("PropertyType");

        Listing listing = new Listing(resultId, listingURL, name, description, neighborhoodOverview,
            pictureUrl, host, neighborhood, accommodates,  bathroomsText, bedrooms, price, hasAvailability,
            numberOfReviews,  firstReview,  lastReview, license, instantBookable, latitude, longitude,
            roomType, propertyType);

        listings.add(listing);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (selectStmt != null) {
        selectStmt.close();
      }
      if (results != null) {
        results.close();
      }
    }
    return listings;
  }



  public Listing updateListingPrice(Listing listing, double newPrice) throws SQLException {
    String updateListingPrice = "UPDATE Listing SET Price=? WHERE ID=?;";

    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet resultSet = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(updateListingPrice);
      selectStmt.setInt(1, listing.getID());
      selectStmt.setDouble(2, newPrice);

      selectStmt.executeUpdate();
      return listing;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (selectStmt != null) {
        selectStmt.close();
      }
    }
  }



  public Listing updateListingAvailability(Listing listing, boolean newAvailability) throws SQLException {
    String updateListing = "UPDATE Listing SET HasAvailability=? WHERE ID=?;";

    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet resultSet = null;

    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(updateListing);
      selectStmt.setInt(1, listing.getID());
      selectStmt.setBoolean(2, newAvailability);

      selectStmt.executeUpdate();
      return listing;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (selectStmt != null) {
        selectStmt.close();
      }
    }
  }



  public Listing delete(Listing listing) throws SQLException {
    String deleteListing = "DELETE FROM Listing WHERE ID=?;";

    Connection connection = null;
    PreparedStatement statement = null;
    try {
      connection = connectionManager.getConnection();
      statement = connection.prepareStatement(deleteListing);
      statement.executeUpdate();
      return null;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (statement != null) {
        statement.close();
      }
    }
  }

}
