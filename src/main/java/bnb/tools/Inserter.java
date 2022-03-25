package bnb.tools;


import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;

import bnb.dal.CalendarDao;
import bnb.model.Calendar;



/**
 * main() runner, used for the app demo.
 * 
 * Instructions:
 * 1. Create a new MySQL schema and then run the CREATE TABLE statements from lecture:
 * http://goo.gl/86a11H.
 * 2. Update ConnectionManager with the correct user, password, and schema.
 */
public class Inserter {

	public static void main(String[] args) throws SQLException {
		// DAO instances.
		CalendarDao calendarDao = CalendarDao.getInstance();
		
		// TODO: Change constructor to account for an extra Listing object once Listing is defined.
		Calendar calendar = new Calendar(0, new Date(0), false, new BigDecimal(123), new BigDecimal(125), 5, 30);
		calendar = calendarDao.create(calendar);
		
		System.out.println(calendarDao.getCalendarByID(calendar.getId()));
		// TODO: Change once Listing is defined
		System.out.println(calendarDao.getCalendarByListingID(2318));
		
		calendar = calendarDao.delete(calendar);
	}
}
