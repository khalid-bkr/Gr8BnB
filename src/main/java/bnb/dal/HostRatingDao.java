package bnb.dal;

import bnb.model.Host;
import bnb.model.HostRating;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * A Host Rating Data Access Object within the Gr8BnB application for CS5200
 * @author Kelly Song
 */
public class HostRatingDao {
  protected ConnectionManager connectionManager;
  private static HostRatingDao instance = null;

  protected HostRatingDao() {
    this.connectionManager = new ConnectionManager();
  }

  public static HostRatingDao getInstance() {
    if (instance == null) {
      instance = new HostRatingDao();
    }
    return instance;
  }

  public HostRating create(HostRating hostRating) throws SQLException {
    String insertHostRating = "INSERT INTO HostRating(HostID,Rating) " +
        "VALUES(?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    ResultSet resultKey = null;

    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertHostRating,
          PreparedStatement.RETURN_GENERATED_KEYS);

      insertStmt.setInt(1, hostRating.getHost().getId());
      insertStmt.setDouble(2, hostRating.getRating());

      insertStmt.executeUpdate();

      resultKey = insertStmt.getGeneratedKeys();
      int hostRatingId = -1;
      if (resultKey.next()) {
        hostRatingId = resultKey.getInt(1);
      } else {
        throw new SQLException("Unable to retrieve auto-generated key.");
      }
      hostRating.setId(hostRatingId);
      return hostRating;
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

  public HostRating getHostRatingById (int id) throws SQLException {
    String selectHostRating = "SELECT ID,HostId,Rating " +
        "FROM HostRating " +
        "WHERE ID=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;

    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectHostRating);
      HostDao hostDao = HostDao.getInstance();
      selectStmt.setInt(1, id);

      results = selectStmt.executeQuery();
      if (results.next()) {
        int resultId = results.getInt("ID");
        Host host = hostDao.getHostById(results.getInt("HostId"));
        double rating = results.getDouble("Rating");

        HostRating hostRating = new HostRating(resultId, host, rating);
        return hostRating;
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

  public List<HostRating> getHostRatingByHostId(int hostId) throws SQLException {
    List<HostRating> hostRatings = new ArrayList<>();
    String selectHostRatings =
        "SELECT ID,HostId,Rating "
            + "FROM HostRating "
            + "WHERE HostId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet resultSet = null;

    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectHostRatings);
      selectStmt.setInt(1, hostId);
      HostDao hostDao = HostDao.getInstance();

      resultSet = selectStmt.executeQuery();
      while (resultSet.next()) {
        int id = resultSet.getInt("ID");
        Host host = hostDao.getHostById(resultSet.getInt("HostId"));
        double rating = resultSet.getDouble("Rating");

        HostRating hostRating = new HostRating(id, host, rating);
        hostRatings.add(hostRating);
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
      if (resultSet != null) {
        resultSet.close();
      }
    }
    return hostRatings;
  }

  public HostRating updateHostRatingRating(HostRating hostRating, double newRating)
      throws SQLException {
    String updateHostRating =
        "UPDATE HostRating "
            + "SET Rating=? "
            + "WHERE ID=?;";

    Connection connection = null;
    PreparedStatement selectStmt = null;
//    ResultSet resultSet = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(updateHostRating);
      selectStmt.setInt(1, hostRating.getId());

      selectStmt.executeUpdate();
      return hostRating;
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

  public HostRating delete(HostRating hostRating) throws SQLException {
    String deleteHostRating = "DELETE FROM Gr8BnBApplication.HostRating WHERE ID=?;";

    Connection connection = null;
    PreparedStatement statement = null;
    try {
      connection = connectionManager.getConnection();
      statement = connection.prepareStatement(deleteHostRating);
      statement.setInt(1, hostRating.getId());
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
