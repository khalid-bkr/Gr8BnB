package bnb.dal;

import bnb.model.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ReviewDao {
	protected ConnectionManager connectionManager;

	private static ReviewDao instance = null;
	protected ReviewDao() {
		connectionManager = new ConnectionManager();
	}
	public static ReviewDao getInstance() {
		if(instance == null) {
			instance = new ReviewDao();
		}
		return instance;
	}

	public Review create(Review review) throws SQLException {
		String insertReview =
			"INSERT INTO Review(ID,Date,ReviewerID,Comments,ListingID) " +
			"VALUES(?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertReview);
			
			insertStmt.setLong(1, review.getId());
			insertStmt.setDate(2, new Date (review.getDate().getTime()));
			insertStmt.setInt(3, review.getReviewerId());
			insertStmt.setString(4, review.getComments());
			insertStmt.setInt(5, review.getListingId());
			
			insertStmt.executeUpdate();
			
			return review;
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
			if(resultKey != null) {
				resultKey.close();
			}
		}
	}

	/**
	 * Delete the Review instance.
	 * This runs a DELETE statement.
	 */
	public Review delete(Review review) throws SQLException {
		String deleteReview = "DELETE FROM Review WHERE ID=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteReview);
			deleteStmt.setLong(1, review.getId());
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

	/**
	 * Get the Review record by fetching it from your MySQL instance by id.
	 * This runs a SELECT statement and returns a single Review instance.
	 */
	public Review getReviewById(Long id) throws SQLException {
		String selectReview = "SELECT * FROM Review WHERE ID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReview);
			
			selectStmt.setLong(1, id);
			results = selectStmt.executeQuery();
			if(results.next()) {
				Long resultId = results.getLong("ID");
				java.util.Date date = new java.util.Date(results.getDate("Date").getTime());
				Integer reviewerId = results.getInt("ReviewerID");
				String comments = results.getString("Comments");
				Integer listingId = results.getInt("ListingID");
				
				Review review = new Review(resultId, date, reviewerId, comments, listingId);
				return review;
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

	/**
	 * Get all Review record by a specified reviewer id.
	 * This runs a SELECT statement and returns a list of Review instances.
	 */
	public List<Review> getReviewsByReviewerId(Integer reviewerId) throws SQLException {
		List<Review> reviews = new ArrayList<Review>();
		String selectReview = "SELECT * FROM Review WHERE ReviewerID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReview);
			selectStmt.setInt(1, reviewerId);
			results = selectStmt.executeQuery();
			while(results.next()) {
				Long id = results.getLong("ID");
				java.util.Date date = new java.util.Date(results.getDate("Date").getTime());
				Integer resultReviewerId = results.getInt("ReviewerID");
				String comments = results.getString("Comments");
				Integer listingId = results.getInt("ListingID");
				
				Review review = new Review(id, date, resultReviewerId, comments, listingId);
				reviews.add(review);
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
		return reviews;
	}
	
	/**
	 * Get all Review record by a specified listing id.
	 * This runs a SELECT statement and returns a list of Review instances.
	 */
	public List<Review> getReviewsByListingId(Integer listingId) throws SQLException {
		List<Review> reviews = new ArrayList<Review>();
		String selectReview = "SELECT * FROM Review WHERE ListingID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReview);
			selectStmt.setInt(1, listingId);
			results = selectStmt.executeQuery();
			while(results.next()) {
				Long id = results.getLong("ID");
				java.util.Date date = new java.util.Date(results.getDate("Date").getTime());
				Integer reviewerId = results.getInt("ReviewerID");
				String comments = results.getString("Comments");
				Integer resultListingId = results.getInt("ListingID");
				
				Review review = new Review(id, date, reviewerId, comments, resultListingId);
				reviews.add(review);
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
		return reviews;
	}
}

