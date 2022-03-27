package bnb.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bnb.model.Amenity;
import bnb.model.ListingAmenity;
import bnb.model.Listing;

public class ListingAmenityDao {
	protected ConnectionManager connectionManager;
	private static ListingAmenityDao instance = null;
	protected ListingAmenityDao() {
		connectionManager = new ConnectionManager();
	}
	public static ListingAmenityDao getInstance() {
		if(instance == null) {
			instance = new ListingAmenityDao();
		}
		return instance;
	}
	
	public ListingAmenity create(ListingAmenity listingAmenity) throws SQLException {
		String insertListingAmenity = "INSERT INTO ListingAmenity(ListingID,AmenityID) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertListingAmenity,Statement.RETURN_GENERATED_KEYS);
			
			insertStmt.setInt(1, listingAmenity.getListing().getID());
			insertStmt.setInt(2, listingAmenity.getAmenity().getId());
			insertStmt.executeUpdate();
			
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int listingAmenityId = -1;
			if(resultKey.next()) {
				listingAmenityId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			listingAmenity.setId(listingAmenityId);;
			return listingAmenity;
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
	
	
	public ListingAmenity getListingAmenityById(int listingAmenityId) throws SQLException {
		String selectListingAmenity = "SELECT ID,ListingID,AmenityID " +
				"FROM ListingAmenity " +
				"WHERE ID=?;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectListingAmenity);
			selectStmt.setInt(1, listingAmenityId);
			results = selectStmt.executeQuery();
			
			ListingDao listingDao = ListingDao.getInstance();
			AmenityDao amenityDao = AmenityDao.getInstance();
			
			if(results.next()) {
				int resultListingAmenityId = results.getInt("ID");
				int listingId = results.getInt("ListingID");
				int amenityId = results.getInt("AmenityID");
				
				Listing listing = listingDao.getListingById(listingId);
				Amenity amenity = amenityDao.getAmenityById(amenityId);

				ListingAmenity listingAmenity = new ListingAmenity(resultListingAmenityId,listing,amenity);
				return listingAmenity;
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
	
	public ListingAmenity delete(ListingAmenity listingAmenity) throws SQLException {
		String deleteListingAmenity = "DELETE FROM ListingAmenity WHERE ID=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteListingAmenity);
			deleteStmt.setInt(1, listingAmenity.getId());
			deleteStmt.executeUpdate();
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
