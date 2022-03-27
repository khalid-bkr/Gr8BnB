package bnb.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bnb.model.Calendar;
import bnb.model.Listing;
import bnb.tools.Util;

/**
 * @author Maidi Wang
 *
 */
public class CalendarDao {
	private static final String SCHEMA = "ListingID,Date,Available,Price,AdjustedPrice,MinimumNights,MaximumNights";
	private static final String TABLE = "Calendar";
	private static final String SCHEMA_TABLE = Util.combine(TABLE, SCHEMA);
	private static final String INSERT = Util.insertStatement(SCHEMA_TABLE);
	private static final String SELECT_BY_ID = Util.selectStatement(TABLE, "*", "ID");
	private static final String SELECT_BY_LISTINGID = Util.selectStatement(TABLE, "*", "ListingID");
	private static final String DELETE = Util.deleteStatement(TABLE, "ID");

	protected ConnectionManager connectionManager;

	protected CalendarDao() {
		connectionManager = new ConnectionManager();
	}

	private static CalendarDao instance = null;

	public static CalendarDao getInstance() {
		if (instance == null) {
			instance = new CalendarDao();
		}
		return instance;
	}

	public Calendar create(Calendar calendar) throws SQLException {
		try (Connection connection = connectionManager.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {

			 statement.setLong(1, calendar.getListing().getID());
			statement.setDate(2, calendar.getDate());
			statement.setBoolean(3, calendar.isAvailable());
			statement.setBigDecimal(4, calendar.getPrice());
			statement.setBigDecimal(5, calendar.getAdjustedPrice());
			statement.setInt(6, calendar.getMinimumNights());
			statement.setInt(7, calendar.getMaximumNights());
			statement.executeUpdate();
			
			ResultSet result = statement.getGeneratedKeys();
			if (result.next()) {
				calendar.setId(result.getInt(1));
			} else {
				throw new SQLException("Could not retrieve generated keys");
			}
			return calendar;
		}
	}

	public Calendar getCalendarByID(int ID) throws SQLException {
		try (Connection connection = connectionManager.getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID)) {
			statement.setInt(1, ID);
			ResultSet results = statement.executeQuery();
			if (results.next()) {
				return new Calendar(ID,
						 ListingDao.getInstance().getListingById(results.getInt("ListingID")),
						results.getDate("Date"), results.getBoolean("Available"), results.getBigDecimal("Price"),
						results.getBigDecimal("AdjustedPrice"), results.getInt("MinimumNights"),
						results.getInt("MaximumNights"));
			}
		}
		return null;
	}

	public List<Calendar> getCalendarByListingID(int listingID) throws SQLException {
		try (Connection connection = connectionManager.getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_BY_LISTINGID)) {
			statement.setInt(1, listingID);
			ResultSet results = statement.executeQuery();
			List<Calendar> calendars = new ArrayList<>();
			 Listing listing = ListingDao.getInstance().getListingById(listingID);
			while (results.next()) {
				calendars.add(new Calendar(results.getInt("ID"),
						 listing,
						results.getDate("Date"), results.getBoolean("Available"), results.getBigDecimal("Price"),
						results.getBigDecimal("AdjustedPrice"), results.getInt("MinimumNights"),
						results.getInt("MaximumNights")));
			}
			return calendars;
		}
	}

	public Calendar delete(Calendar calendar) throws SQLException {
		try (Connection connection = connectionManager.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE)) {
			statement.setInt(1, calendar.getId());
			statement.executeUpdate();
		}
		return null;
	}
}
