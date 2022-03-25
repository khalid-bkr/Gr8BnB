package bnb.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import bnb.model.Guest;
import bnb.model.User;

public class GuestDao extends UserDao {

	private static GuestDao instance = null;
	protected GuestDao() {
		connectionManager = new ConnectionManager();
	}
	public static GuestDao getInstance() {
		if(instance == null) {
			instance = new GuestDao();
		}
		return instance;
	} 
	
	public Guest create(Guest guest) throws SQLException {
		// Insert into the superclass table first.
		create(new User(guest.getId(), guest.getName(), guest.getUserName(), guest.getPassword()));
	
		String insertGuest = "INSERT INTO Guest(ID) VALUES(?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertGuest);
			insertStmt.setInt(1, guest.getId());
			insertStmt.executeUpdate();
			return guest;
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
	
	public Guest getGuestById(int id) throws SQLException {
		String selectGuest = "SELECT Guest.ID, Name, UserName, Password " +
							"FROM User INNER JOIN Guest" +
							"	ON Guest.ID = User.ID " +
							"WHERE Guest.ID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectGuest);
			selectStmt.setInt(1, id);

			results = selectStmt.executeQuery();

			if(results.next()) {
				String resultUserName = results.getString("UserName");
				String password = results.getString("Password");
				String name = results.getString("Name");
				int hostId = results.getInt("Guest.ID");

				
			
				Guest guest = new Guest(hostId,name,resultUserName,password);
				return guest;
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
	
	public Guest delete(Guest guest) throws SQLException {
		String deleteGuest = "DELETE FROM Guest WHERE ID=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteGuest);
			deleteStmt.setInt(1, guest.getId());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for guestId=" + guest.getId());
			}
			
			super.delete(guest);


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
