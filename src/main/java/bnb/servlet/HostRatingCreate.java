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

import bnb.dal.HostRatingDao;
import bnb.model.Host;
import bnb.model.HostRating;

@WebServlet("/hostratingcreate")
public class HostRatingCreate extends HttpServlet {
	protected HostRatingDao hostRatingDao;
	
	@Override
	public void init() throws ServletException {
		hostRatingDao = HostRatingDao.getInstance();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
		req.getRequestDispatcher("/HostRatingCreate.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        String id = req.getParameter("id");
        if (id == null || id.trim().isEmpty()) {
        	messages.put("success", "Invalid ID");
        } else {
        	int hostRatingId = Integer.parseInt(id);
        	int hostId = Integer.parseInt(req.getParameter("hostid"));
        	Host host = new Host(hostId);
        	double rating = Double.parseDouble(req.getParameter("rating"));
        	try {
        		HostRating hostRating= new HostRating(hostRatingId, host, rating);
        		hostRating = hostRatingDao.create(hostRating);
        		messages.put("success", "Successfully created " + hostRatingId);
        	} catch (SQLException e) {
        		e.printStackTrace();
        		throw new IOException(e);
        	}
        }
        req.getRequestDispatcher("/HostRatingCreate.jsp").forward(req, resp);
	}
}
