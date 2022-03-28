package bnb.servlet;



import bnb.dal.*;
import bnb.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/index")
public class Index extends HttpServlet {
	
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

        List<Listing> listings = new ArrayList<Listing>();
        
        

        String neighborhood = req.getParameter("neighborhood");
        if (neighborhood == null || neighborhood.trim().isEmpty()) {
            messages.put("success", "Please enter a valid neighborhood name.");
        } else {
        	// Retrieve Listings by neighborhood, and store as a message.
        	try {
            	listings = listingDao.getListingsByNeighborhood(neighborhood);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + neighborhood);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering Index.jsp.
        	messages.put("previousNeighborhoodName", neighborhood);
        }
        req.setAttribute("neighborhood", neighborhood);
        
        req.getRequestDispatcher("/Index.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Listing> listings = new ArrayList<Listing>();
        // Retrieve and validate name.
        // listings is retrieved from the form POST submission. By default, it
        // is populated by the URL query string (in Index.jsp).
        String neighborhood = req.getParameter("neighborhood");
        if (neighborhood == null || neighborhood.trim().isEmpty()) {
            messages.put("success", "Please enter a valid neighborhood name.");
        } else {
        	// Retrieve Listings, and store as a message.
        	try {
        		listings = listingDao.getListingsByNeighborhood(neighborhood);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + neighborhood);
        }
        req.setAttribute("listings", listings);
        
        req.getRequestDispatcher("/Index.jsp").forward(req, resp);
    }
}
