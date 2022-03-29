package bnb.servlet;

import bnb.dal.*;
import bnb.model.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calendarcreate")
public class CalendarCreate extends HttpServlet {

	protected CalendarDao calendarDao;

	@Override
	public void init() throws ServletException {
		calendarDao = CalendarDao.getInstance();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
		req.getRequestDispatcher("/CalendarCreate.jsp").forward(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
		
		try {
			Listing listing = ListingDao.getInstance().getListingById(Integer.parseInt(req.getParameter("listingid")));
			Calendar calendar = new Calendar(
					0,
					listing,
					Date.valueOf(req.getParameter("date")),
					Boolean.getBoolean(req.getParameter("available")),
					new BigDecimal(req.getParameter("price")),
					new BigDecimal(req.getParameter("adjustedprice")),
					Integer.parseInt(req.getParameter("minimumnights")),
					Integer.parseInt(req.getParameter("maximumnights")));
			calendar = calendarDao.create(calendar);
			messages.put("success", "Successfully created calendar for " + listing.getName());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		
		req.getRequestDispatcher("/CalendarCreate.jsp").forward(req, resp);
	}}
