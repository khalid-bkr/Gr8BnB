package bnb.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bnb.dal.*;
import bnb.model.*;

@WebServlet("/createreview")
public class CreateReview extends HttpServlet {
	protected ReviewDao reviewDao;
	
	@Override
	public void init() throws ServletException {
		reviewDao = ReviewDao.getInstance();
	}
	
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        String listingId = req.getParameter("listingid");
        if (listingId == null || listingId.trim().isEmpty()) {
            messages.put("title", "Invalid listing");
        } else {
        	messages.put("title", "Create a New Review for Listing " + listingId);
        }
        
        req.getRequestDispatcher("/CreateReview.jsp").forward(req, resp);
    }

	
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        int reviewerId = 15; // hard-coded for now

        Date date = new Date();
        String comment = req.getParameter("newcomment");
        int listingId = Integer.parseInt(req.getParameter("listingId"));
        Listing listing = new Listing(listingId);
        Guest guest = new Guest(reviewerId);
        Review review = new Review(date, guest, comment, listing);

        try {
        	review = reviewDao.createWithoutId(review);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        resp.sendRedirect("listingreviews?listingid=" + listingId);

    }
	
	
}
