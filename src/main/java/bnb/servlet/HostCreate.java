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


@WebServlet("/hostcreate")
public class HostCreate extends HttpServlet {
	
	protected HostDao hostDao;
	
	@Override
	public void init() throws ServletException {
		hostDao = HostDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/HostCreate.jsp").forward(req, resp);
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
        	
        	
//        	int guestId = Integer.parseInt(req.getParameter("id"));
        	String name = req.getParameter("name");
        	String username = req.getParameter("username");
        	String password = req.getParameter("password");
        	String hostUrl = req.getParameter("hosturl");
        	String hostLocation = req.getParameter("hostlocation");
        	String hostAbout = req.getParameter("hostabout");
        	Date hostSince = new Date();
        	
	        try {
	        	Host host = new Host(name, username, password, hostUrl, hostSince, hostLocation, hostAbout, 0,0);
	        	host = hostDao.create(host);
	        	messages.put("success", "Successfully created " + userName);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/HostCreate.jsp").forward(req, resp);
    }
}

