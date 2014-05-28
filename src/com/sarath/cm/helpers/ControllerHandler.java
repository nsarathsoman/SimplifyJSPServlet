package com.sarath.cm.helpers;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * ControllerHandler handles the controller classes 
 * This class is responsible for processing the requests from the FrontController
 */
public abstract class ControllerHandler {

	/*
	 * Map<URL, Contoller>
	 * Example : 
	 * url = /any-url 
	 * controller = className.methodName -->
	 * class=org.ex.Class 
	 * method = home there fore 
	 * controller = org.ex.Class.home
	 */
	protected Map<String, String> urlMap = new HashMap<String, String>();

	protected HttpServletRequest request;
	protected HttpServletResponse response;

	public ControllerHandler() {
		feedUrlMap();
	}

	/*
	 * Any Custom ControllerHandler should use this method for feeding 
	 * the url to controller mapping
	 */
	protected abstract void feedUrlMap();

	/*
	 * Searches urlMap for controllers
	 */
	protected String findController(String url) 
			throws ControllerNotFoundException {
		String controller = getUrlMap().get(url);
		if (controller == null)
			throw new ControllerNotFoundException();
		return controller;
	}

	/*
	 * Fetches url from request
	 * Finds controller and gets the Controller class name and 
	 * controller method name
	 */
	public final void invokeController(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		this.request = request;
		this.response = response;
		try {
			String fetchedUrl = request.getRequestURI().substring(
					request.getContextPath().length() + 1);
			String controller = findController(fetchedUrl);
			int indexOfLastDot = controller.lastIndexOf(".");
			String methodName = controller.substring(indexOfLastDot + 1);
			String className = controller.substring(0, indexOfLastDot);
			invokeControllerMethod(className, methodName);
		} catch (ControllerNotFoundException e) {
			handleControllerNotFoundException();
		}

	}	

	/*
	 * Invoke Controller method
	 */
	protected final void invokeControllerMethod(String className,
			String methodName) throws ControllerNotFoundException {
		try {
			Class<?> controller = Class.forName(className);
			Method controllerMethod = controller.getDeclaredMethod(methodName,
					new Class[] { HttpServletRequest.class,
							HttpServletResponse.class });
			controllerMethod.invoke(null, new Object[] { getRequest(),
					getResponse() });

		} catch (Exception e) {
			throw new ControllerNotFoundException();
		}
	}
	
	/*
	 * Any Custom ControllerHandler should use this method to define the Controller Not Found Exception 
	 */
	protected abstract void handleControllerNotFoundException() throws ServletException, IOException;	

	//getters and setters
	
	protected Map<String, String> getUrlMap() {
		return urlMap;
	}

	protected void setUrlMap(Map<String, String> urlMap) {
		this.urlMap = urlMap;
	}

	protected HttpServletRequest getRequest() {
		return request;
	}

	protected void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	protected HttpServletResponse getResponse() {
		return response;
	}

	protected void setResponse(HttpServletResponse response) {
		this.response = response;
	}

}
