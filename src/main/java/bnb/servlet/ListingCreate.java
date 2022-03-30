package bnb.servlet;


import bnb.dal.*;
import bnb.model.*;
import bnb.model.Listing.RoomType;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ListingCreate allows you to create a new listing.
 * 
 * To use locally, start the server and navigate to http://localhost:8080/Gr8BnBApplication/listingcreate
 */
@WebServlet("/listingcreate")
public class ListingCreate extends HttpServlet {
	
	protected ListingDao listingDao;
	
	@Override
	public void init() throws ServletException {
		listingDao = ListingDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/ListingCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String listingId = req.getParameter("listingId");
        if (listingId == null || listingId.trim().isEmpty()) {
            messages.put("success", "Invalid Listing Id");
        } else {
        	
        	int id = Integer.parseInt(req.getParameter("id"));
        	String listingURL = req.getParameter("listingurl");
        	String name = req.getParameter("name");
        	String description = req.getParameter("description");
        	String neighborhoodOverview = req.getParameter("neighborhoodoverview");
        	String pictureUrl = req.getParameter("pictureurl");
        	Host host = new Host(Integer.valueOf(req.getParameter("hostid")));
        	Neighborhood neighborhood = new Neighborhood(req.getParameter("neighborhoodname"));
        	int accommodates = Integer.parseInt(req.getParameter("accommodates"));
        	String bathroomsText = req.getParameter("bathroomstext");
        	int bedrooms = Integer.parseInt(req.getParameter("bedrooms"));
        	double price = Double.parseDouble(req.getParameter("price"));
        	Boolean hasAvailability = Boolean.parseBoolean(req.getParameter("hasavailability"));
        	int numberOfReviews = Integer.parseInt(req.getParameter("numberofreviews"));
        	
        	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        	String firstReviewString = req.getParameter("firstreview");
        	Date firstReview = new Date();
        	try {
        		firstReview = dateFormat.parse(firstReviewString);
        	} catch (ParseException e) {
        		e.printStackTrace();
        		throw new IOException(e);
        	}
        	
        	String lastReviewString = req.getParameter("lastreview");
        	Date lastReview = new Date();
        	try {
        		lastReview = dateFormat.parse(lastReviewString);
        	} catch (ParseException e) {
        		e.printStackTrace();
        		throw new IOException(e);
        	}
        	
        	String license = req.getParameter("license");
        	Boolean instantBookable = Boolean.parseBoolean(req.getParameter("instantbookable"));
        	double latitude = Double.parseDouble(req.getParameter("latitude"));
        	double longitude = Double.parseDouble(req.getParameter("longitude"));
        	RoomType roomType = RoomType.valueOf(req.getParameter("roomtype"));
        	String propertyType = req.getParameter("propertyType");
        	
	        try {
	        	Listing listing = new Listing(id, listingURL, name, description, neighborhoodOverview,
	                    pictureUrl, host, neighborhood, accommodates,  bathroomsText, bedrooms, price, hasAvailability,
	                 numberOfReviews,  firstReview,  lastReview, license, instantBookable, latitude, longitude,
	                    roomType, propertyType);
	        	listing = listingDao.create(listing);
	        	messages.put("success", String.format("Successfully created listing '%s' with id %d", name, id));
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/ListingCreate.jsp").forward(req, resp);
    }
}
