package com.sarath.cm.helpers;

import java.io.IOException;

import javax.servlet.ServletException;
/*
 * A Custom implementation of ControllerHanlder 
 * Controller are mapped with url's statically
 * 
 * We can have an XMLControllerHandler where this url-controller
 * mapping can be done in XML file
 * 			
 * 			or
 * 
 * We can have an AnnotationControllerHandler where url-controller
 * mapping can be done via annotations 
 * 
 */
public class StaticControllerHandler extends ControllerHandler {	

	@Override
	protected void feedUrlMap() {
		getUrlMap().put("", "com.sarath.cm.controllers.HomeController.index");
		getUrlMap().put("home", "com.sarath.cm.controllers.HomeController.home");
		getUrlMap().put("login", "com.sarath.cm.controllers.HomeController.login");
	}

	@Override
	protected void handleControllerNotFoundException() throws ServletException, IOException {
		getResponse().setStatus(404);
		getRequest().getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
	}

}
