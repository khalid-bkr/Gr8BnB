package bnb.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bnb.dal.UserDao;
import bnb.model.*;


public class UserDao {
	protected ConnectionManager connectionManager;
	
	private static UserDao instance = null;
	protected UserDao() {
		connectionManager = new ConnectionManager();
	}
	public static UserDao getInstance() {
		if(instance == null) {
			instance = new UserDao();
		}
		return instance;
	}

	public User create(User user) throws SQLException {
		String insertUser = "INSERT INTO User(ID, Name, UserName, Password) VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertUser);
			
			insertStmt.setInt(1, user.getId());
			insertStmt.setString(2, user.getName());
			insertStmt.setString(3, user.getUserName());
			insertStmt.setString(4, user.getPassword());

			insertStmt.executeUpdate();

			return user;
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
	
	public User getUserById(int id) throws SQLException {
		String selectUser = "SELECT ID, Name, UserName, Password " +
							"FROM User" +
							"WHERE ID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUser);
			selectStmt.setInt(1, id);

			results = selectStmt.executeQuery();

			if(results.next()) {
				String resultUserName = results.getString("UserName");
				String password = results.getString("Password");
				String name = results.getString("Name");
				int Id = results.getInt("Guest.ID");

				
			
				User user = new Guest(Id,name,resultUserName,password);
				return user;
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
	
	public User getUserByUserName(String userName) throws SQLException {
		String selectUser = "SELECT ID, Name, UserName, Password FROM User WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUser);
			selectStmt.setString(1, userName);

			results = selectStmt.executeQuery();

			if(results.next()) {
				String resultUserName = results.getString("UserName");
				String password = results.getString("Password");
				String name = results.getString("Name");
				int id = results.getInt("ID");
			
				User user = new User(id,name,resultUserName,password);
				return user;
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
	
	public User delete(User user) throws SQLException {
		String deleteUser = "DELETE FROM User WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteUser);
			deleteStmt.setString(1, user.getUserName());
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
