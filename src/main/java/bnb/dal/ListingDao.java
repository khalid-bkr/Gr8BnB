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
        + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

    Connection connection = null;
    PreparedStatement insertStmt = null;
//    ResultSet resultKey = null;
    
    try {
          connection = connectionManager.getConnection();
          insertStmt = connection.prepareStatement(insertListing);
          HostDao hostDao = HostDao.getInstance();

          insertStmt.setInt(1, listing.getID());
          insertStmt.setString(2, listing.getListingURL());
          insertStmt.setString(3, listing.getName());
          insertStmt.setString(4, listing.getDescription());
          insertStmt.setString(5, listing.getNeighborhoodOverview());
          insertStmt.setString(6, listing.getPictureUrl());
          insertStmt.setInt(7, listing.getHost().getId());
          insertStmt.setString(8, listing.getNeighborhood().getNeighborhood());
          insertStmt.setInt(9, listing.getAccommodates());
          insertStmt.setString(10, listing.getBathroomsText());
          insertStmt.setInt(11, listing.getBedrooms());
          insertStmt.setDouble(12, listing.getPrice());
          insertStmt.setBoolean(13, listing.isHasAvailability());
          insertStmt.setInt(14, listing.getNumberOfReviews());
          insertStmt.setTimestamp(15, new Timestamp(listing.getFirstReview().getTime()));
          insertStmt.setTimestamp(16, new Timestamp(listing.getLastReview().getTime()));
          insertStmt.setString(17, listing.getLicense());
          insertStmt.setBoolean(18, listing.isInstantBookable());
          insertStmt.setDouble(19, listing.getLatitude());
          insertStmt.setDouble(20, listing.getLongitude());
          insertStmt.setString(21, listing.getRoomType().getRoom());
          insertStmt.setString(22, listing.getPropertyType());

          insertStmt.executeUpdate();
          
          hostDao.incrementHostListingCount(listing.getHost());
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
    String selectListing = "SELECT * FROM Listing WHERE ID=?;";

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



  public List<Listing> getListingsByHostId(int hostId) throws SQLException {
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


  public List<Listing> getListingsByPriceLimit(double maxPrice) throws SQLException {
    List<Listing> listings = new ArrayList<>();
    String selectListings = "SELECT * FROM Listing WHERE Price <=?;";

    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;

    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectListings);
      selectStmt.setDouble(1, maxPrice);
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
//    ResultSet resultSet = null;
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
//    ResultSet resultSet = null;

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
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteListing);
      HostDao hostDao = HostDao.getInstance();
      
      deleteStmt.setInt(1, listing.getID());
      deleteStmt.executeUpdate();
      
      hostDao.decrementHostListingCount(listing.getHost());
      return null;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (deleteStmt != null) {
    	  deleteStmt.close();
      }
    }
  }
  
  /**
	* Increment the NumberOfReviews for a given listing by 1.
	* This runs an UPDATE statement.
	*/
  public Listing incrementNumberOfReviews(Listing listing) throws SQLException {
      Connection connection = null;
      String updateListingNumReviews = "UPDATE Listing SET NumberOfReviews=NumberOfReviews + 1 WHERE ID=?;";
      PreparedStatement updateStmt = null;

      try {
          connection = connectionManager.getConnection();
          updateStmt = connection.prepareStatement(updateListingNumReviews);
          updateStmt.setInt(1, listing.getID());
          updateStmt.executeUpdate();

          listing.setNumberOfReviews(listing.getNumberOfReviews() + 1);
          return listing;
      } catch (SQLException e) {
          e.printStackTrace();
          throw e;
      } finally {
          if (connection != null) {
              connection.close();
          }
          if (updateStmt != null) {
              updateStmt.close();
          }
      }
  }
  
  /**
	* Decrement the NumberOfReviews for a given listing by 1.
	* This runs an UPDATE statement.
	*/
  public Listing decrementNumberOfReviews(Listing listing) throws SQLException {
      Connection connection = null;
      String updateListingNumReviews = "UPDATE Listing SET NumberOfReviews=NumberOfReviews - 1 WHERE ID=?;";
      PreparedStatement updateStmt = null;

      try {
          connection = connectionManager.getConnection();
          updateStmt = connection.prepareStatement(updateListingNumReviews);
          updateStmt.setInt(1, listing.getID());
          updateStmt.executeUpdate();

          listing.setNumberOfReviews(listing.getNumberOfReviews() - 1);
          return listing;
      } catch (SQLException e) {
          e.printStackTrace();
          throw e;
      } finally {
          if (connection != null) {
              connection.close();
          }
          if (updateStmt != null) {
              updateStmt.close();
          }
      }
  }
  
  /**
	* Update the FirstReview date of a given listing.
	* This runs an UPDATE statement.
	*/
  public Listing updateFirstReview(Listing listing, Date reviewDate) throws SQLException {
	    String updateListingFirstReview = "UPDATE Listing SET FirstReview=? WHERE ID=?;";

	    Connection connection = null;
	    PreparedStatement updateStmt = null;

	    try {
	      connection = connectionManager.getConnection();
	      updateStmt = connection.prepareStatement(updateListingFirstReview);
	      updateStmt.setDate(1, new java.sql.Date (reviewDate.getTime()));
	      updateStmt.setInt(1, listing.getID());

	      updateStmt.executeUpdate();
	      
	      listing.setFirstReview(reviewDate);
	      
	      return listing;
	    } catch (SQLException e) {
	      e.printStackTrace();
	      throw e;
	    } finally {
	      if (connection != null) {
	        connection.close();
	      }
	      if (updateStmt != null) {
	    	  updateStmt.close();
	      }
	    }
	  }
  
  /**
	* Update the LastReview date of a given listing.
	* This runs an UPDATE statement.
	*/
  public Listing updateLastReview(Listing listing, Date reviewDate) throws SQLException {
	    String updateListingFirstReview = "UPDATE Listing SET LastReview=? WHERE ID=?;";

	    Connection connection = null;
	    PreparedStatement updateStmt = null;

	    try {
	      connection = connectionManager.getConnection();
	      updateStmt = connection.prepareStatement(updateListingFirstReview);
	      updateStmt.setDate(1, new java.sql.Date (reviewDate.getTime()));
	      updateStmt.setInt(1, listing.getID());

	      updateStmt.executeUpdate();
	      
	      listing.setLastReview(reviewDate);
	      
	      return listing;
	    } catch (SQLException e) {
	      e.printStackTrace();
	      throw e;
	    } finally {
	      if (connection != null) {
	        connection.close();
	      }
	      if (updateStmt != null) {
	    	  updateStmt.close();
	      }
	    }
	  }
  
  /**
	* Gets the last review date of a given listing.
	* This runs an SELECT statement and returns the date as a java.util.Date)
	*/
  public Date getLastReviewDateOfListing (Listing listing) throws SQLException {
	    String selectLastReviewDateOfListing = 
	    		"SELECT Review.Date " +
	    		"FROM Listing INNER JOIN Review " +
	    		"WHERE Listing.ID=? " + 
	    	    "ORDER BY Review.Date DESC " +
	    	    "LIMIT 1;";

	    Connection connection = null;
	    PreparedStatement selectStmt = null;
	    ResultSet results = null;

	    try {
	      connection = connectionManager.getConnection();
	      selectStmt = connection.prepareStatement(selectLastReviewDateOfListing);
	      selectStmt.setInt(1, listing.getID());

	      results = selectStmt.executeQuery();

	      if (results.next()) {
	        Date resultLastReviewDate = new java.util.Date(results.getDate("Date").getTime());

	        return resultLastReviewDate;
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
}
