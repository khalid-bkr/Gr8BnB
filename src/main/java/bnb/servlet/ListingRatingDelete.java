package bnb.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bnb.dal.ListingRatingDao;
import bnb.model.ListingRating;

@WebServlet("/listingratingdelete")
public class ListingRatingDelete extends HttpServlet{
	
	protected ListingRatingDao listingRatingDao;
	
	@Override
	public void init() throws ServletException {
		listingRatingDao = ListingRatingDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Provide a title and render the JSP.
        messages.put("title", "Delete ListingRating");        
        req.getRequestDispatcher("/ListingRatingDelete.jsp").forward(req, resp);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String listingratingid = req.getParameter("listingratingid");
        if (listingratingid == null || listingratingid.trim().isEmpty()) {
            messages.put("title", "Invalid ListingRating Id");
            messages.put("disableSubmit", "true");
        } else {
        	// Delete the ListingRating.
        	ListingRating listingRating = new ListingRating(Integer.valueOf(listingratingid));
	        try {
	        	listingRating = listingRatingDao.delete(listingRating);
	        	// Update the message.
	        	if(listingRating == null) {
	        		messages.put("title", "Successfully deleted ListingRating " + listingratingid);
		            messages.put("disableSubmit", "true");
	        	}else {
	        		messages.put("title", "Failed to delete ListingRating " + listingratingid);
		        	messages.put("disableSubmit", "false");
	        	}
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/ListingRatingDelete.jsp").forward(req, resp);
    }
}
