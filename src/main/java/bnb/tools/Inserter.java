package bnb.tools;


import java.math.BigDecimal;
import java.sql.SQLException;


import bnb.dal.UserDao;
import bnb.model.User;

import bnb.dal.CalendarDao;
import bnb.dal.GuestDao;
import bnb.dal.HostDao;
import bnb.dal.ListingRatingDao;
import bnb.dal.NeighborhoodDao;
import bnb.model.Calendar;
import bnb.model.Guest;
import bnb.model.Host;
import bnb.model.ListingRating;


import java.util.Date;
import bnb.model.Neighborhood;

import java.util.List;


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
		HostDao hostDao = HostDao.getInstance();
		GuestDao guestDao = GuestDao.getInstance();
		CalendarDao calendarDao = CalendarDao.getInstance();
    	ListingRatingDao listingRatingDao = ListingRatingDao.getInstance();
		NeighborhoodDao neighborhoodDao = NeighborhoodDao.getInstance();

		
		
//		User u1 = userDao.getUserByUserName("Kat15");
//		System.out.format("Reading USER: id:%s n:%s un:%s \n",
//		u1.getId(), u1.getName(), u1.getUserName());
		
//		User user = new User(436188584,"Bruce","Bruce127","password");
//		user = userDao.create(user);
//		User u2 = userDao.getUserByUserName("Bruce127");
//		System.out.format("Reading USER: id:%s n:%s un:%s \n",
//		u2.getId(), u2.getName(), u2.getUserName());
		
//		userDao.delete(u2);
//		User u3 = userDao.getUserByUserName("Bruce127");
//		System.out.format("Reading USER: id:%s n:%s un:%s \n",
//		u3.getId(), u3.getName(), u3.getUserName());
		
//		host test
//		Date date = new Date();
//		Host host = new Host(436188584,"Bruce","Bruce127","password","https://www.airbnb.com/users/show/2536",date, "Seattle, Washington, United States", "im new here", 0, 0);
//		host = hostDao.create(host);
//		System.out.format("Reading USER: id:%s n:%s un:%s hs:%s, ha:%s \n",
//				host.getId(), host.getName(), host.getUserName(), host.getHostSince(), host.getHostAbout());
//		
//		
//		Host h1 = hostDao.getHostByUserName("Bruce127");
//		System.out.format("Reading USER: id:%s n:%s un:%s hs:%s, ha:%s \n",
//		h1.getId(), h1.getName(), h1.getUserName(), h1.getHostSince(), h1.getHostAbout());
//		
//		hostDao.delete(h1);
		
//		guest test
		
//		Guest guest = new Guest(436188585,"Bruce","Bruce128","password");
//		guest = guestDao.create(guest);
//		System.out.format("Reading USER: id:%s n:%s un:%s \n",
//		guest.getId(), guest.getName(), guest.getUserName());
//		
//		Guest g1 = guestDao.getGuestById(436188585);
//		System.out.format("Reading USER: id:%s n:%s un:%s \n",
//				g1.getId(), g1.getName(), g1.getUserName());
//		
//		System.out.print(guestDao.delete(guest));
		
		
		
		
		
		
		

//		CalendarDao calendarDao = CalendarDao.getInstance();
//    ListingRatingDao listingRatingDao = ListingRatingDao.getInstance();
//		
//		// TODO: Change constructor to account for an extra Listing object once Listing is defined.
//		Calendar calendar = new Calendar(0, new Date(0), false, new BigDecimal(123), new BigDecimal(125), 5, 30);
//		calendar = calendarDao.create(calendar);
//		
//		System.out.println(calendarDao.getCalendarByID(calendar.getId()));
//		// TODO: Change once Listing is defined
//		System.out.println(calendarDao.getCalendarByListingID(2318));
//		
//		calendar = calendarDao.delete(calendar);


    // TODO: doesn't work yet, depends on Listing and Host tables, and replace below accordingly
    // Create
    ListingRating listingRating; int listingId, hostId;
//    listingRating = new ListingRating(2318, 2536, ListingRating.ScoreType.Rating, 0.1);
//    System.out.println("Before Insert: " + listingRating);  // 0.1 rating for differentiation here
//    listingRating = listingRatingDao.create(listingRating);  // created with ID generated
//    System.out.println("After Insert: " + listingRating);
    // Read
//    listingId = 2318;
//    listingRating = listingRatingDao.getListingRatingById(listingRating.getListingId());
//    System.out.println(listingRating);
//    List<ListingRating> listingRatings = listingRatingDao.getListRatingByListingId(listingId);
//    for (ListingRating lr : listingRatings) System.out.println(lr);
    // Update
//    System.out.println("Before Update: " + listingRating);
//    listingRating = listingRatingDao.updateListRating(listingRating, 5.1);
//    System.out.println("After Update: " + listingRating);  // 5.1 rating for differentiation
    // Delete
//    listingRatingDao.delete(listingRating);


		// Create a neighborhood
		Neighborhood neighborhood = new Neighborhood("testNeighborhood", "testNeighborhoodGroup");
		neighborhood = neighborhoodDao.create(neighborhood);
		System.out.println("********** create neighborhood ************");
		System.out.println(neighborhood.getNeighborhood());
		System.out.println(neighborhood.getNeighborhoodGroup());
		System.out.println("");

		// Get a neighborhood by neighborhood name
		neighborhood = neighborhoodDao.getNeighborhoodFromNeighborhood("testNeighborhood");
		System.out.println("********** get a neighborhood by neighborhood name ************");
		System.out.println(neighborhood.getNeighborhood());
		System.out.println(neighborhood.getNeighborhoodGroup());
		System.out.println("");

		// Update a neighborhood group
		neighborhood = neighborhoodDao.updateNeighborhoodGroup(neighborhood, "updated testNeighborhoodGroup");
		System.out.println("********** update neighborhood group ************");
		System.out.println(neighborhood.getNeighborhood());
		System.out.println(neighborhood.getNeighborhoodGroup());
		System.out.println("");

		// Delete a neighborhood
		neighborhood = neighborhoodDao.deleteNeighborhood(neighborhood);
		System.out.println("********** delete neighborhood ************");
		System.out.println(neighborhood);
  }
}
