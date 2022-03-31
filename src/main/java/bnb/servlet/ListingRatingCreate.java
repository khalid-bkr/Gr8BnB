package bnb.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bnb.dal.ListingDao;
import bnb.dal.ListingRatingDao;
import bnb.model.Host;
import bnb.model.Listing;
import bnb.model.ListingRating;
import bnb.model.ListingRating.ScoreType;

@WebServlet("/listingratingcreate")
public class ListingRatingCreate extends HttpServlet{
	protected ListingRatingDao listingRatingDao;
	
	@Override
	public void init() throws ServletException {
		listingRatingDao = ListingRatingDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        req.getRequestDispatcher("/ListingRatingCreate.jsp").forward(req, resp);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

    	int listingId = Integer.parseInt(req.getParameter("listingid"));
        Listing listing = new Listing(listingId);
        int hostId = Integer.parseInt(req.getParameter("hostid"));
        Host host = new Host(hostId);
        ListingRating.ScoreType scoreType =
                ListingRating.ScoreType.valueOf(req.getParameter("scoretype"));
        double score = Double.valueOf(req.getParameter("score"));
        
        ListingRating listingRating = new ListingRating(listing,host,scoreType,score);
        
        try {
			listingRating = listingRatingDao.create(listingRating);
			messages.put("success", "Successfully created ListingRating " + listingRating.getId()); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
        req.getRequestDispatcher("/ListingRatingCreate.jsp").forward(req, resp);
	}
}