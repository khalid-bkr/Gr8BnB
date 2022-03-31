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
 * ListingUpdate allows you to delete a listing.
 * 
 * To use locally, start the server and navigate to http://localhost:8080/Gr8BnBApplication/listingdelete?listingid=0
 */
@WebServlet("/listingdelete")
public class ListingDelete extends HttpServlet {
	
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
        messages.put("title", "Delete Listing");
        req.getRequestDispatcher("/ListingDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate listing id.
        String listingId = req.getParameter("listingId");
        if (listingId == null || listingId.trim().isEmpty()) {
            messages.put("title", "Invalid listing ID.");
            messages.put("disableSubmit", "true");
        } else {
        	Listing listing = new Listing(Integer.parseInt(listingId));
        	try {
        		listing = listingDao.delete(listing);
        		// Update the message
        		if (listing == null) {
        			messages.put("title", "Successfully deleted listing with ID: " + listingId);
                    messages.put("disableSubmit", "true");
        		} else {
        			messages.put("title", "Failed to delete listing with ID: " + listingId);
                    messages.put("disableSubmit", "false");
        		}
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/ListingDelete.jsp").forward(req, resp);
    }
}
