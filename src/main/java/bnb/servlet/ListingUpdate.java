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
 * To use locally, start the server and navigate to http://localhost:8080/Gr8BnBApplication/listingupdate?listingid=0&price=0
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
        String listingId = req.getParameter("listingid");
        if (listingId == null || listingId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid listing id");
        } else {
        	try {
        		Listing listing = listingDao.getListingById(Integer.parseInt(listingId));
        		if (listing == null) {
        			messages.put("success", String.format("Listing with id '%d' does not exist.", listingId));
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

        // Retrieve and validate name.
        String listingId = req.getParameter("listingId");
        if (listingId == null || listingId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid listing id");
        } else {
        	
        	try {
        		Listing listing = listingDao.getListingById(Integer.parseInt(listingId));
        		if (listing == null) {
        			messages.put("success", String.format("Listing with id '%d' does not exist. No update to perform.", listingId));
        		} else {
        			String priceString = req.getParameter("price");
        			if (priceString == null || priceString.trim().isEmpty())  {
        	            messages.put("success", "Please enter a valid listing id");
        			} else {
        				double newPrice = 0.0;
        				try {
        					newPrice = Double.parseDouble(priceString);
        				} catch (NumberFormatException e) {
        					e.printStackTrace();
        					throw new IOException(e);
        				}
        				
        				// Price given is a double
        				double oldPrice = listing.getPrice();
        				listing = listingDao.updateListingPrice(listing, newPrice);
        				messages.put("success", String.format("Successfully changed listing price from $%.2f to $%.2f", oldPrice, newPrice));
        			}
        		}
        		req.setAttribute("listing", listing);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/ListingUpdate.jsp").forward(req, resp);
    }
}
