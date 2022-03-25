package bnb.dal;

import bnb.model.ListingRating;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListingRatingDao {
  protected ConnectionManager connectionManager;
  private static ListingRatingDao instance = null;
  public static ListingRatingDao getInstance() {
    if (instance == null)
      instance = new ListingRatingDao();
    return instance;
  }

  protected ListingRatingDao() {
    connectionManager = new ConnectionManager();
  }

  public ListingRating create(ListingRating listingRating) throws SQLException {
    String insertOne =
      "INSERT INTO Gr8BnBApplication.ListingRating(ListingID, HostID, ScoreType, Score)"
        + "VALUES(?,?,?,?);";

    try (Connection connection = connectionManager.getConnection();
      PreparedStatement statement = connection.prepareStatement(insertOne)) {
      statement.setInt(1, listingRating.getListingId());
      statement.setInt(2, listingRating.getHostId());
      statement.setString(3, listingRating.getScoreType().name());
      statement.setDouble(4, listingRating.getScore());

      statement.executeUpdate();
      ResultSet key = statement.getGeneratedKeys();
      int listRatingId = -1;
      if (key.next()) {
        listRatingId = key.getInt(1);
      } else {
        throw new SQLException("Unable to get auto-generated key: restaurantId");
      }

      listingRating.setListingId(listRatingId);
      return listingRating;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    }
  }

  public ListingRating getListingRatingById(int listingRatingId) throws SQLException {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet res = null;

    String selectOne =
      "SELECT "
        + "ID, "
        + "ListingID, "
        + "HostID, "
        + "ScoreType, "
        + "Score "
    + "FROM Gr8BnBApplication.ListingRating "
    + "WHERE id=?;";

    try {
      connection = connectionManager.getConnection();
      statement = connection.prepareStatement(selectOne);
      statement.setInt(1, listingRatingId);

      res = statement.executeQuery();
      if (res.next()) {
        int id = res.getInt("ID");
        int listingId = res.getInt("ListingID");
        int hostId = res.getInt("HostID");
        ListingRating.ScoreType scoreType =
          ListingRating.ScoreType.valueOf(res.getString("ScoreType"));
        double score = res.getDouble("Score");

        return new ListingRating(id, listingId, hostId, scoreType, score);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) connection.close();
      if (statement != null) statement.close();
      if (res != null) res.close();
    }
    return null;
  }

  public List<ListingRating> getListRatingByListingId(int listingId) throws SQLException {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet res = null;

    String selectMany =
      "SELECT "
        + "ID, "
        + "ListingID, "
        + "HostID, "
        + "ScoreType, "
        + "Score "
    + "FROM Gr8BnBApplication.ListingRating "
    + "WHERE ListingID=?;";

    List<ListingRating> listingRatings = new ArrayList<>();
    try {
      connection = connectionManager.getConnection();
      statement = connection.prepareStatement(selectMany);
      statement.setInt(1, listingId);

      res = statement.executeQuery();
      while (res.next()) {
        int id = res.getInt("ID");
        int hostId = res.getInt("HostID");
        ListingRating.ScoreType scoreType =
          ListingRating.ScoreType.valueOf(res.getString("ScoreType"));
        double score = res.getDouble("Score");

        ListingRating listingRating = new ListingRating(id, hostId, listingId, scoreType, score);
        listingRatings.add(listingRating);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) connection.close();
      if (statement != null) statement.close();
      if (res != null) res.close();
    }

    return listingRatings;
  }

  public ListingRating updateListRating(ListingRating listingRating, double newScore) throws SQLException {
    String updateOne =
      "UPDATE Gr8BnBApplication.ListingRating "
    + "SET Score=? "
    + "WHERE ID=?;";

    try (Connection connection = connectionManager.getConnection();
      PreparedStatement statement = connection.prepareStatement(updateOne)) {
      statement.setDouble(1, newScore);
      statement.setInt(2, listingRating.getId());

      statement.executeUpdate();
      listingRating.setScore(newScore);
      return listingRating;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    }
  }

  public ListingRating delete(ListingRating listingRating) throws SQLException {
    String deleteOne = "DELETE FROM Gr8BnBApplication.ListingRating WHERE ID=?;";

    try (Connection connection = connectionManager.getConnection();
      PreparedStatement statement = connection.prepareStatement(deleteOne)) {
      statement.setInt(1, listingRating.getId());
      statement.executeUpdate();

      return null;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    }
  }
}
