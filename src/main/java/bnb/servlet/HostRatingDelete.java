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

@WebServlet("/hostratingdelete")
public class HostRatingDelete extends HttpServlet{
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
        messages.put("title", "Delete HostRating");        
        req.getRequestDispatcher("/HostRatingDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        String id = req.getParameter("hostratingid");
        if (id == null || id.trim().isEmpty()) {
            messages.put("title", "Invalid ID");
            messages.put("disableSubmit", "true");
        } else {
        	int hostRatingId = Integer.parseInt(id);
    		HostRating hostRating= new HostRating(hostRatingId);
        	try {
        		hostRating = hostRatingDao.delete(hostRating);
        		if (hostRating == null) {
		            messages.put("title", "Successfully deleted " + id);
		            messages.put("disableSubmit", "true");
        		} else {
		        	messages.put("title", "Failed to delete " + id);
		        	messages.put("disableSubmit", "false");
        		}
        	} catch (SQLException e) {
        		e.printStackTrace();
        		throw new IOException(e);
        	}
        }
        req.getRequestDispatcher("/HostRatingDelete.jsp").forward(req, resp);
	}
}
