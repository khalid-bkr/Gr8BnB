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
        
        HostDao hostDao = HostDao.getInstance();
        	
    	String name = req.getParameter("name");
    	String description = req.getParameter("description");
    	String neighborhoodOverview = req.getParameter("neighborhoodoverview");
    	String pictureUrl = req.getParameter("pictureurl");
    	
    	Integer hostId = Integer.valueOf(req.getParameter("hostid"));
    	Host host = null;
		try {
			host = hostDao.getHostById(hostId);
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new IOException(e1);
		}
		
		if (host == null) {
			messages.put("success", "Host ID entered is not a valid host ID.");
		}
    	
    	Neighborhood neighborhood = new Neighborhood(req.getParameter("neighborhoodname"));
    	int accommodates = Integer.parseInt(req.getParameter("accommodates"));
    	String bathroomsText = req.getParameter("bathroomstext");
    	int bedrooms = Integer.parseInt(req.getParameter("bedrooms"));
    	double price = Double.parseDouble(req.getParameter("price"));
    	Boolean hasAvailability = Boolean.parseBoolean(req.getParameter("hasavailability").toLowerCase());
    	
    	String license = req.getParameter("license");
    	Boolean instantBookable = Boolean.parseBoolean(req.getParameter("instantbookable").toLowerCase());
    	double latitude = Double.parseDouble(req.getParameter("latitude"));
    	double longitude = Double.parseDouble(req.getParameter("longitude"));
    	
    	RoomType roomType = null;
    	String roomTypeString = req.getParameter("roomtype");
    	
    	for (RoomType type : RoomType.values()) {
            if (type.getRoom().equals(roomTypeString)) {
            	roomType = type;
            }
        }
    	if (roomType == null) {
    		messages.put("success", "Invalid room type.");
    	}
    	String propertyType = req.getParameter("propertyType");
    	
        try {
        	Listing listing = new Listing(null, name, description, neighborhoodOverview,
                    pictureUrl, host, neighborhood, accommodates,  bathroomsText, bedrooms, price, hasAvailability,
                 0,  null,  null, license, instantBookable, latitude, longitude,
                    roomType, propertyType);
        	listing = listingDao.create(listing);
        	messages.put("success", String.format("Successfully created listing '%s'", name));
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        
        req.getRequestDispatcher("/ListingCreate.jsp").forward(req, resp);
    }
}
