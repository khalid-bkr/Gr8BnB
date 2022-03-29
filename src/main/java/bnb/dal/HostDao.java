package bnb.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import bnb.model.Host;
import bnb.model.User;





public class HostDao extends UserDao {

	private static HostDao instance = null;
	protected HostDao() {
		connectionManager = new ConnectionManager();
	}
	public static HostDao getInstance() {
		if(instance == null) {
			instance = new HostDao();
		}
		return instance;
	}


	public Host create(Host host) throws SQLException {
		// Insert into the superclass table first.
		UserDao userDao = UserDao.getInstance();
		User parent = create(new User(host.getName(), host.getUserName(), host.getPassword()));
		parent = userDao.create(parent);
		String insertHost = "INSERT INTO Host(ID, HostUrl, HostSince, HostLocation, HostAbout, HostListingsCount, HostTotalListingsCount) VALUES(?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertHost);
			insertStmt.setInt(1, parent.getId());
			insertStmt.setString(2, host.getHostUrl());
			insertStmt.setTimestamp(3, new Timestamp(host.getHostSince().getTime()));
			insertStmt.setString(4, host.getHostLocation());
			insertStmt.setString(5, host.getHostAbout());
//			create a host with 0 listings and increase once a listing is created with the hostId from listings
			insertStmt.setInt(6, 0);
			insertStmt.setInt(7, 0);
			insertStmt.executeUpdate();
			
			host.setId(parent.getId());
			return host;
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
	
	public Host getHostById(int id) throws SQLException {
		String selectHost = "SELECT Host.ID, Name, UserName, Password, HostUrl, HostSince, HostLocation, HostAbout, HostListingsCount, HostTotalListingsCount " +
							"FROM User INNER JOIN Host" +
							"	ON Host.ID = User.ID " +
							"WHERE Host.ID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectHost);
			selectStmt.setInt(1, id);

			results = selectStmt.executeQuery();

			if(results.next()) {
				String resultUserName = results.getString("UserName");
				String password = results.getString("Password");
				String name = results.getString("Name");
				int hostId = results.getInt("Host.ID");
				Date hostSince = new Date(results.getTimestamp("HostSince").getTime());
				String hostLocation = results.getString("HostLocation");
				String hostAbout = results.getString("HostAbout");
				String hostUrl = results.getString("HostUrl");
				int hostListingsCount = results.getInt("HostListingsCount");
				int hostTotalListingsCount = results.getInt("HostTotalListingsCount");
				
			
				Host host = new Host(hostId,name,resultUserName,password,hostUrl,hostSince,hostLocation,hostAbout,hostListingsCount,hostTotalListingsCount);
				return host;
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
	
	public Host getHostByUserName(String userName) throws SQLException {
		String selectHost = "SELECT Host.ID, Name, UserName, Password, HostUrl, HostSince, HostLocation, HostAbout, HostListingsCount, HostTotalListingsCount " +
							"FROM User INNER JOIN Host" +
							"	ON Host.ID = User.ID " +
							"WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectHost);
			selectStmt.setString(1, userName);

			results = selectStmt.executeQuery();

			if(results.next()) {
				String resultUserName = results.getString("UserName");
				String password = results.getString("Password");
				String name = results.getString("Name");
				int hostId = results.getInt("Host.ID");
				Date hostSince = new Date(results.getTimestamp("HostSince").getTime());
				String hostLocation = results.getString("HostLocation");
				String hostAbout = results.getString("HostAbout");
				String hostUrl = results.getString("HostUrl");
				int hostListingsCount = results.getInt("HostListingsCount");
				int hostTotalListingsCount = results.getInt("HostTotalListingsCount");
				
			
				Host host = new Host(hostId,name,resultUserName,password,hostUrl,hostSince,hostLocation,hostAbout,hostListingsCount,hostTotalListingsCount);
				return host;
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
	
    public Host incrementHostListingCount(Host host) throws SQLException {
        Connection connection = null;
        String updateHost = "UPDATE Host SET HostListingsCount=HostListingsCount + 1, HostTotalListingsCount=HostTotalListingsCount+ 1 WHERE ID=?;";
        PreparedStatement updateStmt = null;

        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateHost);
            updateStmt.setInt(1, host.getId());
            updateStmt.executeUpdate();

            host.setHostListingCount(host.getHostListingCount() + 1);
            host.setHostTotalListingCount(host.getHostTotalListingCount() + 1);
            return host;
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
    
    public Host decrementHostListingCount(Host host) throws SQLException {
        Connection connection = null;
        String updateHost = "UPDATE Host SET HostListingsCount=HostListingsCount - 1, HostTotalListingsCount=HostTotalListingsCount - 1 WHERE ID=?;";
        PreparedStatement updateStmt = null;

        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateHost);
            updateStmt.setInt(1, host.getId());
            updateStmt.executeUpdate();

            host.setHostListingCount(host.getHostListingCount() - 1);
            host.setHostTotalListingCount(host.getHostTotalListingCount() - 1);
            return host;
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
	
	public Host delete(Host host) throws SQLException {
		String deleteHost = "DELETE FROM Host WHERE ID=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteHost);
			deleteStmt.setInt(1, host.getId());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for hostId=" + host.getId());
			}
			
			super.delete(host);

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
