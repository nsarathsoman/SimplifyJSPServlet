package com.sarath.cm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sarath.cm.helpers.ControllerHandler;

/**
 * Servlet implementation class FrontController
 */
@WebServlet(urlPatterns = { "/" }, initParams = { @WebInitParam(name = "controllerHandler", value = "com.sarath.cm.helpers.StaticControllerHandler") })
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FrontController() {
		super();
	}

	@Override
	public void init() throws ServletException {
		String controllerhandler = getServletConfig().getInitParameter(
				"controllerHandler");
		try {
			getServletContext().setAttribute("controllerHandlerObject",
					Class.forName(controllerhandler).newInstance());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		((ControllerHandler) getServletContext().getAttribute(
				"controllerHandlerObject")).invokeController(request, response);
	}

}
