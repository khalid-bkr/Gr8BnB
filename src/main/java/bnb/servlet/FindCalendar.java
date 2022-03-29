package bnb.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bnb.dal.CalendarDao;
import bnb.model.Calendar;

@WebServlet("/findcalendar")
public class FindCalendar extends HttpServlet {

	protected CalendarDao calendarDao;

	@Override
	public void init() throws ServletException {
		calendarDao = CalendarDao.getInstance();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String> messages = new HashMap<>();
		req.setAttribute("messages", messages);

		List<Calendar> calendars = null;

		String listingid = req.getParameter("listingid");
		if (listingid == null || listingid.trim().isEmpty()) {
			messages.put("success", "Please enter a valid listingid.");
		} else {
			try {
				calendars = calendarDao.getCalendarByListingID(Integer.parseInt(listingid));
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
			messages.put("success", "Displaying results for " + listingid);
		}
		req.setAttribute("calendars", calendars);

		req.getRequestDispatcher("FindCalendar.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String> messages = new HashMap<>();
		req.setAttribute("messages", messages);

		List<Calendar> calendars = null;

		String listingid = req.getParameter("listingid");
		if (listingid == null || listingid.trim().isEmpty()) {
			messages.put("success", "Please enter a valid listingid.");
		} else {
			try {
				calendars = calendarDao.getCalendarByListingID(Integer.parseInt(listingid));
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
			messages.put("success", "Displaying results for " + listingid);
		}
		req.setAttribute("calendars", calendars);

		req.getRequestDispatcher("FindCalendar.jsp").forward(req, resp);
	}

}
