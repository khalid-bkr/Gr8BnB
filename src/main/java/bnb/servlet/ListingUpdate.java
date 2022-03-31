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
 * ListingUpdate allows you to update a listing's price.
 * 
 * To use locally, start the server and navigate to http://localhost:8080/Gr8BnBApplication/listingupdate?listingId=0&price=0
 */
@WebServlet("/listingupdate")
public class ListingUpdate extends HttpServlet {
	
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
        
        // Retrieve Listing and validate.
        String listingId = req.getParameter("listingId");
        if (listingId == null || listingId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid listing ID.");
        } else {
        	try {
        		Listing listing = listingDao.getListingById(Integer.parseInt(listingId));
        		if (listing == null) {
        			messages.put("success", String.format("Listing with ID '%d' does not exist.", listingId));
        		}
        		req.setAttribute("listing", listing);
        	} catch (SQLException e) {
        		e.printStackTrace();
        		throw new IOException(e);
        	}
        }
        
        req.getRequestDispatcher("/ListingUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String listingId = req.getParameter("listingId");
        if (listingId == null || listingId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid listing ID.");
        } else {
        	try {
        		Listing listing = listingDao.getListingById(Integer.valueOf(listingId));
        		if(listing == null) {
        			messages.put("success", String.format("Listing with listing ID %s does not exist. No update to perform.", listingId));
        		} else {
        			String newPrice = req.getParameter("price");
        			if (newPrice == null || newPrice.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid price in the format '0.00'.");
        	        } else {
        	        	Double price;
        	        	try {
        	        	  price = Double.parseDouble(newPrice.trim());
        	        	} catch(NumberFormatException e) {
        	        		e.printStackTrace();
        	        		throw new IOException(e);
        	        	}
        	        	Double oldPrice = listing.getPrice();
        	        	listing = listingDao.updateListingPrice(listing, price);
        	        	messages.put("success", String.format("Successfully changed listing price from $%.2f to $%.2f.", oldPrice, price));
        	        }
        		}
        		req.setAttribute("listing", listing);
        	} catch (SQLException e1) {
				e1.printStackTrace();
				throw new IOException(e1);
	        }
        }
        
        req.getRequestDispatcher("/ListingUpdate.jsp").forward(req, resp);
    }
}
