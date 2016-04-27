package com.airline.controllers;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.airline.models.Passenger;

/**
 * Application Lifecycle Listener implementation class AirLineListener
 *
 */
@WebListener
public class AirLineListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public AirLineListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent event)  { 
    	ServletContext sc = event.getServletContext();
    	
    	ArrayList<Passenger> pList = (ArrayList<Passenger>)sc.getAttribute("passengers");
    	
    	if(pList == null) {
    		System.out.println("No passenger list created yet. Let's create the list here...");
    		pList = new ArrayList<Passenger>();
    		sc.setAttribute("passengers", pList);
    	}
    }
	
}
