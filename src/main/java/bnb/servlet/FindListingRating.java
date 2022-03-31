package bnb.servlet;

import bnb.dal.ListingRatingDao;
import bnb.model.ListingRating;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/findlistingrating")
public class FindListingRating extends HttpServlet{
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


    List<ListingRating> listingRatings = null;
    
    String listingId = req.getParameter("listingid");
    if (listingId == null || listingId.trim().isEmpty()) {
      messages.put("success", "Please enter a valid listing id.");
    } else {
      try {
        listingRatings = listingRatingDao.getListRatingByListingId(Integer.parseInt(listingId));
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
      messages.put("success", "Displaying results for Listing: " + listingId);
    }
    req.setAttribute("listingRatings", listingRatings);
    
    req.getRequestDispatcher("FindListingRating.jsp").forward(req, resp);
  }
  
  
  
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	Map<String, String> messages = new HashMap<>();
	req.setAttribute("messages", messages);

	List<ListingRating> listingRatings = null;

	String listingid = req.getParameter("listingid");
	if (listingid == null || listingid.trim().isEmpty()) {
		messages.put("success", "Please enter a valid listingid.");
	} else {
		try {
			listingRatings = listingRatingDao.getListRatingByListingId(Integer.parseInt(listingid));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		messages.put("success", "Displaying results for Listing: " + listingid);
	}
	req.setAttribute("listingRatings", listingRatings);

	req.getRequestDispatcher("FindListingRating.jsp").forward(req, resp);
  }

}
