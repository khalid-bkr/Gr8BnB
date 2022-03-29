package bnb.servlet;
import bnb.model.*;
import bnb.dal.*;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bnb.dal.ReviewDao;

@WebServlet("/deletereview")
public class DeleteReview extends HttpServlet{
	protected ReviewDao reviewDao;
	
	@Override
	public void init() throws ServletException {
		reviewDao = ReviewDao.getInstance();
	}
	
	@Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		Long reviewid = Long.parseLong(req.getParameter("reviewid"));
		Review review = new Review(reviewid);
		try {
			review = reviewDao.delete(review);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
		int listingid = Integer.parseInt(req.getParameter("listingid"));
		resp.sendRedirect("listingreviews?listingid=" + listingid);
	}
	
	
}
