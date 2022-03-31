package bnb.servlet;

import bnb.dal.ListingRatingDao;
import bnb.model.ListingRating;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/updatelistingrating")
public class UpdateListingRating extends HttpServlet{
	
  protected ListingRatingDao listingRatingDao;

  @Override
  public void init() throws ServletException {
    listingRatingDao = ListingRatingDao.getInstance();
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);


    String ratingId = req.getParameter("ratingid");
    String newRating = req.getParameter("newRating");
    String listingId = null;    

    if (ratingId == null || ratingId.trim().isEmpty()) {
      messages.put("success", "Please enter a valid rating id.");
    } else {
      try {
    	double rating =  Double.valueOf(newRating);
    	ListingRating listingRating = listingRatingDao.getListingRatingById(Integer.valueOf(ratingId));
    	listingRating = listingRatingDao.updateListRating(listingRating, rating);
		listingId = String.valueOf(listingRating.getListing().getID());

      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
      messages.put("success", "Updated results for Rating: " + ratingId + " to : " + newRating);
    }

    resp.sendRedirect("findlistingrating?listingid=" + listingId);
  }

  
  

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Map<String, String> messages = new HashMap<>();
    req.setAttribute("messages", messages);


    String ratingid = req.getParameter("ratingid");
    ListingRating listingRating = null;
    
    if (ratingid == null || ratingid.trim().isEmpty()) {
      messages.put("title", "Invalid rating id");
    } else {
      try {
        listingRating = listingRatingDao.getListingRatingById(Integer.parseInt(ratingid));
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
      messages.put("success", "Ready to update for Rating: " + ratingid);
    }
    
    req.setAttribute("listingRating", listingRating);

    req.getRequestDispatcher("UpdateListingRating.jsp").forward(req, resp);
  }

}