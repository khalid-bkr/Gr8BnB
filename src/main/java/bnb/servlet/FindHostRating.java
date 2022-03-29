package bnb.servlet;

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

import bnb.dal.HostRatingDao;
import bnb.model.HostRating;

@WebServlet("/findhostrating")
public class FindHostRating extends HttpServlet {
	protected HostRatingDao hostRatingDao;
	
	@Override
	public void init() throws ServletException {
		hostRatingDao = HostRatingDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        List<HostRating> hostRatings = new ArrayList<>();
        String hostId = req.getParameter("hostId");
        if (hostId == null || hostId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid host id.");
        } else {
        	try {
        		hostRatings = hostRatingDao.getHostRatingByHostId(Integer.parseInt(hostId));
        	} catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
        	}
        	messages.put("success", "Displaying results for " + hostId);
        	messages.put("previousHostRatingHostId", hostId);
        }
        req.setAttribute("hostRatings", hostRatings);
        req.getRequestDispatcher("/FindHostRating.jsp").forward(req, resp);
	}
}
