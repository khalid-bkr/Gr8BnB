package bnb.tools;


import bnb.dal.HostRatingDao;
import bnb.dal.ListingDao;
import bnb.model.HostRating;
import bnb.model.Listing;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import bnb.dal.UserDao;
import bnb.model.User;

import bnb.dal.CalendarDao;
import bnb.dal.GuestDao;
import bnb.dal.HostDao;
import bnb.dal.ListingRatingDao;
import bnb.dal.NeighborhoodDao;
import bnb.dal.ReviewDao;
import bnb.model.Calendar;
import bnb.model.Guest;
import bnb.model.Host;
import bnb.model.ListingRating;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import bnb.model.Neighborhood;
import bnb.model.Review;

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
		HostRatingDao hostRatingDao = HostRatingDao.getInstance();
		ListingRatingDao listingRatingDao = ListingRatingDao.getInstance();
		NeighborhoodDao neighborhoodDao = NeighborhoodDao.getInstance();
		ReviewDao reviewDao = ReviewDao.getInstance();
		ListingDao listingDao = ListingDao.getInstance();
		
		
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
//		Host h1 = hostDao.getHostByUserName("Bruce127");
//		h1 = hostDao.delete(h1);
		
//		Listing l1 = listingDao.getListingById(53904425);
//		l1 = listingDao.delete(l1);
//		Neighborhood n1 = neighborhoodDao.getNeighborhoodFromNeighborhood("testNeighborhood");
//		n1 = neighborhoodDao.deleteNeighborhood(n1);
		Date date = new Date();
		Host host = new Host(436188584,"Bruce","Bruce127","password","https://www.airbnb.com/users/show/2536",date, "Seattle, Washington, United States", "im new here", 0, 0);
		host = hostDao.create(host);
		System.out.format("Reading USER: id:%s n:%s un:%s hs:%s, ha:%s listingcount:%s totalLCount: %s \n",
				host.getId(), host.getName(), host.getUserName(), host.getHostSince(), host.getHostAbout(), host.getHostListingCount(), host.getHostTotalListingCount());
		
		Neighborhood neighborhood = new Neighborhood("testNeighborhood", "testNeighborhoodGroup");
		neighborhood = neighborhoodDao.create(neighborhood);
		
		Listing listing = new Listing(53904425, "https://www.airbnb.com/rooms/53904424", "testListing", "Our spacious 1 bedroom, 1 bath home", "nice neighborhood", "pic url", host, neighborhood, 2, "bathroom text", 1, 200, true, 0, date, date, "STR-OPLI-21-000328", false, 0, 0,Listing.RoomType.ENTIRE, "apt");
		listing = listingDao.create(listing);
		
		System.out.format("Reading Listing: id:%s n:%s hostID:%s \n",
				listing.getID(), listing.getName(), listing.getHost().getId());
		
		System.out.format("Reading USER: id:%s n:%s un:%s hs:%s, ha:%s listingcount:%s totalLCount: %s \n",
				host.getId(), host.getName(), host.getUserName(), host.getHostSince(), host.getHostAbout(), host.getHostListingCount(), host.getHostTotalListingCount());
		neighborhood = neighborhoodDao.deleteNeighborhood(neighborhood);
		listing = listingDao.delete(listing);
		System.out.format("Reading USER: id:%s n:%s un:%s hs:%s, ha:%s listingcount:%s totalLCount: %s \n",
				host.getId(), host.getName(), host.getUserName(), host.getHostSince(), host.getHostAbout(), host.getHostListingCount(), host.getHostTotalListingCount());
		host = hostDao.delete(host);
//		Host h1 = hostDao.getHostByUserName("Bruce127");
//		System.out.format("Reading USER: id:%s n:%s un:%s hs:%s, ha:%s \n",
//		h1.getId(), h1.getName(), h1.getUserName(), h1.getHostSince(), h1.getHostAbout());
		

		
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

		// Host Rating
		// Create
//		Host host = new Host(1, "hostName", "hostUserName", "password", "hostUrl", new Date(), "hostLocation",
//				"hostAbout", 100, 100);
//		HostRating hostRating1 = new HostRating(1, host, 5.0);
//		HostRating hostRating2 = new HostRating(2, host, 5.0);
//		hostRatingDao.create(hostRating1);
//		System.out.println("********** Create Host Rating ************");
//		System.out.println("ID: " + hostRating1.getId());
//		System.out.println("HostId: " + hostRating1.getHost().getId());
//		System.out.println("Rating: " + hostRating1.getRating());
//		System.out.println("");
//
//		// Read
//		hostRating1 = hostRatingDao.getHostRatingById(1);
//		System.out.println("********** Get a Host Rating by Host Rating ID ************");
//		System.out.println("ID: " + hostRating1.getId());
//		System.out.println("HostId: " + hostRating1.getHost().getId());
//		System.out.println("Rating: " + hostRating1.getRating());
//		System.out.println("");
//
//		List<HostRating> hostRatings = hostRatingDao.getHostRatingByHostId(1);
//		System.out.println("********** Get a Host Rating by Host ID ************");
//		for (HostRating hostRating : hostRatings) {
//			System.out.println("ID: " + hostRating.getId());
//			System.out.println("HostId: " + hostRating.getHost().getId());
//			System.out.println("Rating: " + hostRating.getRating());
//			System.out.println("");
//		}
//
//		// Update
//		hostRating1 = hostRatingDao.updateHostRatingRating(hostRating1, 2.5);
//		System.out.println("********** Update Host Rating Rating ************");
//		System.out.println("ID: " + hostRating1.getId());
//		System.out.println("HostId: " + hostRating1.getHost().getId());
//		System.out.println("Rating: " + hostRating1.getRating());
//		System.out.println("");
//
//		// Delete
//		hostRating1 = hostRatingDao.delete(hostRating1);
//		System.out.println("********** Delete a Host Rating ************");
//		System.out.println(hostRating1);
//		System.out.println("");

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
//		Neighborhood neighborhood = new Neighborhood("testNeighborhood", "testNeighborhoodGroup");
//		neighborhood = neighborhoodDao.create(neighborhood);
//		System.out.println("********** create neighborhood ************");
//		System.out.println(neighborhood.getNeighborhood());
//		System.out.println(neighborhood.getNeighborhoodGroup());
//		System.out.println("");
//
//		// Get a neighborhood by neighborhood name
//		neighborhood = neighborhoodDao.getNeighborhoodFromNeighborhood("testNeighborhood");
//		System.out.println("********** get a neighborhood by neighborhood name ************");
//		System.out.println(neighborhood.getNeighborhood());
//		System.out.println(neighborhood.getNeighborhoodGroup());
//		System.out.println("");
//
//		// Update a neighborhood group
//		neighborhood = neighborhoodDao.updateNeighborhoodGroup(neighborhood, "updated testNeighborhoodGroup");
//		System.out.println("********** update neighborhood group ************");
//		System.out.println(neighborhood.getNeighborhood());
//		System.out.println(neighborhood.getNeighborhoodGroup());
//		System.out.println("");
//
//		// Delete a neighborhood
//		neighborhood = neighborhoodDao.deleteNeighborhood(neighborhood);
//		System.out.println("********** delete neighborhood ************");
//		System.out.println(neighborhood);
		
		
		// REVIEWS - not yet tested as it relies on Listing
		
		// Create Reviews
		System.out.println("********** create review ************");
		String sampleDateString = "2018-10-01";
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		Date sampleDate = null;
		try {
			sampleDate = dateFormatter.parse(sampleDateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if (sampleDate != null) {
			Review review1 = new Review(Long.valueOf(1), sampleDate, 1, "Lovely place to stay!", 1);
			review1 = reviewDao.create(review1);
			System.out.format("\tCreating review: id: %d, date: %s, reviewerId: %d, comments: %s, listingId: %s\n", 
					review1.getId(), review1.getDate().toString(), review1.getReviewerId(), review1.getComments(), review1.getListingId());
			Review review2 = new Review(Long.valueOf(2), sampleDate, 46, "Excellent!", 30);
			review2 = reviewDao.create(review2);
			System.out.format("\tCreating review: id: %d, date: %s, reviewerId: %d, comments: %s, listingId: %s\n", 
					review2.getId(), review2.getDate().toString(), review2.getReviewerId(), review2.getComments(), review2.getListingId());
			Review review3 = new Review(Long.valueOf("323532651363"), null, 1, "Nice and cozy", 30);
			review3 = reviewDao.create(review3);
		}
		
		// Get Review by Id
		System.out.println("********** get review by id ************");
		List<String> reviewIdStrings = new ArrayList<>(Arrays.asList("1", "2", "323532651363"));
		
		for (String idString : reviewIdStrings) {
			Review review = reviewDao.getReviewById(Long.parseLong(idString));
			System.out.format("\tReading review with id '%s': id: %d, date: %s, reviewerId: %d, comments: %s, listingId: %s\n",
					idString, review.getId(), review.getDate().toString(), review.getReviewerId(), review.getComments(), review.getListingId());
		}
		
		// Get Reviews by ReviewerId
		System.out.println("********** get reviews by reviewerId ************");
		Integer chosenReviewerId = 1;
		List<Review> reviewsWithReviewerId = reviewDao.getReviewsByReviewerId(chosenReviewerId);
		
		for (Review review : reviewsWithReviewerId) {
			System.out.format("\tReading review with reviewerId '%d': id: %d, date: %s, reviewerId: %d, comments: %s, listingId: %s\n",
					chosenReviewerId, review.getId(), review.getDate().toString(), review.getReviewerId(), review.getComments(), review.getListingId());
		}
		
		// Get Reviews by ListingId
		System.out.println("********** get reviews by listingId ************");
		Integer chosenListingId = 30;
		List<Review> reviewsWithListingId = reviewDao.getReviewsByReviewerId(chosenListingId);
		
		for (Review review : reviewsWithListingId) {
			System.out.format("\tReading review with listingId '%d': id: %d, date: %s, reviewerId: %d, comments: %s, listingId: %s\n",
					chosenListingId, review.getId(), review.getDate().toString(), review.getReviewerId(), review.getComments(), review.getListingId());
		}
		
		// Delete Reviews
		System.out.println("********** get review by listingId ************");
		for (String idString : reviewIdStrings) {
			Review review = reviewDao.getReviewById(Long.parseLong(idString));
			reviewDao.delete(review);
			System.out.format("\tDeleting review with id %d.\n", idString);
		}
  }
}
