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


@WebServlet("/calendardelete")
public class CalendarDelete extends HttpServlet {
	
	protected CalendarDao calendarDao;
	
	@Override
	public void init() throws ServletException {
		calendarDao = CalendarDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        messages.put("title", "Delete Calendar");
        req.getRequestDispatcher("/CalendarDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        String calendarid = req.getParameter("calendarid");
        if (calendarid == null || calendarid.trim().isEmpty()) {
            messages.put("title", "Invalid CalendarId");
            messages.put("disableSubmit", "true");
        } else {
        	Calendar calendar;
			try {
				calendar = calendarDao.getCalendarByID(Integer.parseInt(calendarid));
				calendar = calendarDao.delete(calendar);
		        if (calendar == null) {
		            messages.put("title", "Successfully deleted " + calendarid);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete " + calendarid);
		        	messages.put("disableSubmit", "false");
		        }
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
        }
        
        req.getRequestDispatcher("/CalendarDelete.jsp").forward(req, resp);
    }
}

