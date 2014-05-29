package com.sarath.cm.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * Controller class example
 * All controller methods should be public static void and should have 
 * HttpServletRequest and HttpServletResponse as parameters
 */
public class HomeController {
	
	/*
	 * controller for /
	 */
	public static void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		RequestDispatcher rd = request
				.getRequestDispatcher("/WEB-INF/template.jsp");		
		request.setAttribute("title", "Index page");
		request.setAttribute("message", "Welcome to index page");
		rd.forward(request, response);
	}
	
	/*
	 * controller for /home
	 */
	public static void home(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		RequestDispatcher rd = request
				.getRequestDispatcher("/WEB-INF/template.jsp");		
		request.setAttribute("title", "Home page");
		request.setAttribute("message", "Welcome to Home page");
		rd.forward(request, response);
	}
	
	/*
	 * controller for /login
	 */
	public static void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/template.jsp");
		request.setAttribute("title", "Login");
		request.setAttribute("message", "Welcome to Login panel");
		rd.include(request, response);
	}

}
