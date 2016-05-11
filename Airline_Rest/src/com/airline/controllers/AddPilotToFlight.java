package com.airline.controllers;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.airline.service.FlightService;

/**
 * Servlet implementation class AddPilotToFlight
 */
@WebServlet("/AddPilotToFlight")
public class AddPilotToFlight extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@EJB
	FlightService fs;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPilotToFlight() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String pid = request.getParameter("pid");
		String fid = request.getParameter("fid");
		
		fs.addPilotToFlight(pid, fid);
		
//		http://localhost:8080/jpa1/AddPilotToFlight?fid=3&pid=5
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
