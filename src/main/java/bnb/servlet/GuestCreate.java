package bnb.servlet;


import bnb.dal.*;
import bnb.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/guestcreate")
public class GuestCreate extends HttpServlet {
	
	protected GuestDao guestDao;
	
	@Override
	public void init() throws ServletException {
		guestDao = GuestDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/GuestCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String userName = req.getParameter("username");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("success", "Invalid UserName");
        } else {
        	// Create the BlogUser.
        	
        	 new Guest(436188585,"Bruce","Bruce128","password");
        	
        	int guestId = Integer.parseInt(req.getParameter("id"));
        	String name = req.getParameter("name");
        	String username = req.getParameter("username");
        	String password = req.getParameter("password");
	        try {
	        	// Exercise: parse the input for StatusLevel.
	        	Guest guest = new Guest(guestId, name, username, password);
	        	guest = guestDao.create(guest);
	        	messages.put("success", "Successfully created " + userName);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/GuestCreate.jsp").forward(req, resp);
    }
}
