package bnb.dal;

import bnb.model.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

	/**
	 * Create a Review instance.
	 * This runs a CREATE statement and returns the resulting Review.
	 */
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
			insertStmt.setInt(3, review.getGuest().getId());
			insertStmt.setString(4, review.getComments());
			insertStmt.setInt(5, review.getListing().getID());
			
			insertStmt.executeUpdate();
			
			ListingDao listingDao = ListingDao.getInstance();
			Listing associatedListing = listingDao.getListingById(review.getListing().getID());
			
			// Increment NumberOfReviews for the associated listing
			listingDao.incrementNumberOfReviews(associatedListing);
			
			// Update FirstReview of the associated listing
			if (associatedListing.getFirstReview() == null || (associatedListing.getFirstReview() != null && associatedListing.getFirstReview().after(review.getDate()))) {
				listingDao.updateFirstReview(associatedListing, review.getDate());
			}
			
			// Update LastReview of the associated listing
			if (associatedListing.getLastReview() == null || (associatedListing.getLastReview() != null && associatedListing.getLastReview().before(review.getDate()))) {
				listingDao.updateLastReview(associatedListing, review.getDate());
			}
					
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

    public Review createWithoutId(Review review) throws SQLException {
        String insertReview =
			"INSERT INTO Review(Date,ReviewerID,Comments,ListingID) " +
			"VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertReview, Statement.RETURN_GENERATED_KEYS);

			insertStmt.setDate(1, new Date (review.getDate().getTime()));
			insertStmt.setInt(2, review.getGuest().getId());
			insertStmt.setString(3, review.getComments());
			insertStmt.setInt(4, review.getListing().getID());
			insertStmt.executeUpdate();

            resultKey = insertStmt.getGeneratedKeys();
			Long reviewId = -1l;
			if (resultKey.next()) {
			    reviewId = resultKey.getLong(1);
			} else {
			    throw new SQLException("Unable to retrieve auto-generated key.");
			}
			review.setId(reviewId);

			ListingDao listingDao = ListingDao.getInstance();
			Listing associatedListing = listingDao.getListingById(review.getListing().getID());

			// Increment NumberOfReviews for the associated listing
			listingDao.incrementNumberOfReviews(associatedListing);

			// Update FirstReview of the associated listing
			if (associatedListing.getFirstReview() == null || (associatedListing.getFirstReview() != null && associatedListing.getFirstReview().after(review.getDate()))) {
				listingDao.updateFirstReview(associatedListing, review.getDate());
			}

			// Update LastReview of the associated listing
			if (associatedListing.getLastReview() == null || (associatedListing.getLastReview() != null && associatedListing.getLastReview().before(review.getDate()))) {
				listingDao.updateLastReview(associatedListing, review.getDate());
			}
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
			review = this.getReviewById(review.getId());
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteReview);
			deleteStmt.setLong(1, review.getId());
			
			deleteStmt.executeUpdate();
			
			ListingDao listingDao = ListingDao.getInstance();
			Listing associatedListing = listingDao.getListingById(review.getListing().getID());
			
			// Decrement NumberOfReviews for the associated listing
			listingDao.decrementNumberOfReviews(associatedListing);
			
			// Update FirstReview of the associated listing
			if (associatedListing.getFirstReview() != null && associatedListing.getFirstReview() == review.getDate()) {
				listingDao.updateFirstReview(associatedListing, null);
			}
			
			// Update LastReview of the associated listing
			if (associatedListing.getLastReview() != null && associatedListing.getLastReview() == review.getDate()) {
				
				// Get the replacement last date
				java.util.Date replacementLastReviewDate = listingDao.getLastReviewDateOfListing(associatedListing);
				if (replacementLastReviewDate == null) {
					listingDao.updateLastReview(associatedListing, null);
				} else {
					listingDao.updateLastReview(associatedListing, replacementLastReviewDate);
				}
			}

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
		String selectReview =
				"SELECT ID, Date, ReviewerID, Comments, ListingID " +
				"FROM Review " +
				"WHERE ID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReview);
			
			selectStmt.setLong(1, id);
			results = selectStmt.executeQuery();
			GuestDao guestDao = GuestDao.getInstance();
			ListingDao listingDao = ListingDao.getInstance();
			
			if(results.next()) {
				Long resultId = results.getLong("ID");
				java.util.Date date = new java.util.Date(results.getDate("Date").getTime());
				Integer reviewerId = results.getInt("ReviewerID");
				String comments = results.getString("Comments");
				Integer listingId = results.getInt("ListingID");
				
				Guest guest = guestDao.getGuestById(reviewerId);
				Listing listing = listingDao.getListingById(listingId);
				
				Review review = new Review(resultId, date, guest, comments, listing);
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
		String selectReview =
				"SELECT ID, Date, ReviewerID, Comments, ListingID " +
				"FROM Review " +
				"WHERE ID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReview);
			selectStmt.setInt(1, reviewerId);
			results = selectStmt.executeQuery();
			GuestDao guestDao = GuestDao.getInstance();
			ListingDao listingDao = ListingDao.getInstance();
			
			while(results.next()) {
				Long id = results.getLong("ID");
				java.util.Date date = new java.util.Date(results.getDate("Date").getTime());
				Integer resultReviewerId = results.getInt("ReviewerID");
				String comments = results.getString("Comments");
				Integer listingId = results.getInt("ListingID");
				
				Guest guest = guestDao.getGuestById(resultReviewerId);
				Listing listing = listingDao.getListingById(listingId);
				
				Review review = new Review(id, date, guest, comments, listing);
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
		String selectReview = 
				"SELECT ID, Date, ReviewerID, Comments, ListingID " +
				"FROM Review " +
				"WHERE ListingID=? " +
				"ORDER BY ID DESC;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReview);
			selectStmt.setInt(1, listingId);
			results = selectStmt.executeQuery();
			GuestDao guestDao = GuestDao.getInstance();
			ListingDao listingDao = ListingDao.getInstance();
			
			while(results.next()) {
				Long id = results.getLong("ID");
				java.util.Date date = new java.util.Date(results.getDate("Date").getTime());
				Integer reviewerId = results.getInt("ReviewerID");
				String comments = results.getString("Comments");
				Integer resultListingId = results.getInt("ListingID");
				
				Guest guest = guestDao.getGuestById(reviewerId);
				Listing listing = listingDao.getListingById(resultListingId);
				
				Review review = new Review(id, date, guest, comments, listing);
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

