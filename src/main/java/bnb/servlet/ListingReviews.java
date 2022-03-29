package bnb.servlet;

import bnb.dal.*;
import bnb.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/listingreviews")
public class ListingReviews extends HttpServlet {
    protected ReviewDao reviewDao;

    @Override
    public void init() throws ServletException {
        reviewDao = ReviewDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Review> reviews = new ArrayList<>();

        String listingId = req.getParameter("listingid");
        if (listingId == null || listingId.trim().isEmpty()) {
            messages.put("title", "Invalid listing");
        } else {
            try {
                reviews = reviewDao.getReviewsByListingId(Integer.parseInt(listingId));
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
            messages.put("title", "Reviews for Listing " + listingId);
        }
        req.setAttribute("reviews", reviews);
        req.getRequestDispatcher("ListingReviews.jsp").forward(req, resp);
    }
}
