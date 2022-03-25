package bnb.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bnb.model.Amenity;



public class AmenityDao {
	
	protected ConnectionManager connectionManager;
	private static AmenityDao instance = null;
	protected AmenityDao() {
		connectionManager = new ConnectionManager();
	}
	public static AmenityDao getInstance() {
		if(instance == null) {
			instance = new AmenityDao();
		}
		return instance;
	}
	
	
	public Amenity create(Amenity amenity) throws SQLException {
		String insertAmenity = "INSERT INTO Amenity(Title) VALUES(?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertAmenity,Statement.RETURN_GENERATED_KEYS);
			
			insertStmt.setString(1, amenity.getTitle());
			insertStmt.executeUpdate();
			
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int amenityId = -1;
			if(resultKey.next()) {
				amenityId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			amenity.setId(amenityId);;
			return amenity;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
		}
	}
	
	
	public Amenity getAmenityById(int amenityId) throws SQLException {
		String selectAmenity = "SELECT ID,Title " +
				"FROM Amenity " +
				"WHERE ID=?;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAmenity);
			selectStmt.setInt(1, amenityId);
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				int resultAmenityId = results.getInt("ID");
				String title = results.getString("Title");

				Amenity amenity = new Amenity(resultAmenityId,title);
				return amenity;
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
	
	public Amenity delete(Amenity amenity) throws SQLException {
		String deleteAmenity = "DELETE FROM Amenity WHERE ID=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteAmenity);
			deleteStmt.setInt(1, amenity.getId());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Persons instance.
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}
}
