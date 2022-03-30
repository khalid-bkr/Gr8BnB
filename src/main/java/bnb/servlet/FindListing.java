package bnb.servlet;

import bnb.dal.*;
import bnb.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * FindListing allows you to get a listing by listing id.
 * 
 * To use locally, start the server and navigate to http://localhost:8080/Gr8BnBApplication/findlisting?listingid=0
 */
@WebServlet("/findlisting")
public class FindListing extends HttpServlet {
	
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

        
        Listing listing = null;
        
        // Retrieve and validate lsiting id.
        // listing id is retrieved from the URL query string.
        String listingId = req.getParameter("listingid");
        if (listingId == null || listingId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid listing id.");
        } else {
        	// Retrieve BlogUsers, and store as a message.
        	try {
            	listing = listingDao.getListingById(Integer.parseInt(listingId));
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for listing with id: " + listingId);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindUsers.jsp.
        	messages.put("previousListingId", listingId);
        }
        req.setAttribute("listing", listing);
        
        req.getRequestDispatcher("/FindListing.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        Listing listing = null;
        
        // Retrieve and validate.
        // listingId is retrieved from the form POST submission. By default, it
        // is populated by the URL query string (in FindListing.jsp).
        String listingId = req.getParameter("listingId");
        if (listingId == null || listingId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid listing id.");
        } else {
        	// Retrieve Listing, and store as a message.
        	try {
            	listing = listingDao.getListingById(Integer.parseInt(listingId));
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for listing with id: " + listingId);
        }
        req.setAttribute("listing", listing);
        
        req.getRequestDispatcher("/FindListing.jsp").forward(req, resp);
    }
}
