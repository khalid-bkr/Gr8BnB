package bnb.tools;


import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;


import bnb.dal.UserDao;
import bnb.model.User;

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

		UserDao userDao = UserDao.getInstance();
		
		User u1 = userDao.getUserByUserName("Kat15");
		System.out.format("Reading USER: id:%s n:%s un:%s \n",
		u1.getId(), u1.getName(), u1.getUserName());
		
//		User user = new User(436188584,"Bruce","Bruce127","password");
//		user = userDao.create(user);
		User u2 = userDao.getUserByUserName("Bruce127");
		System.out.format("Reading USER: id:%s n:%s un:%s \n",
		u2.getId(), u2.getName(), u2.getUserName());
		
		userDao.delete(u2);
		User u3 = userDao.getUserByUserName("Bruce127");
		System.out.format("Reading USER: id:%s n:%s un:%s \n",
		u3.getId(), u3.getName(), u3.getUserName());

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
