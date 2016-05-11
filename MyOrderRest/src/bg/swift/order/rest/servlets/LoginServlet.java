package bg.swift.order.rest.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bg.swift.order.rest.dao.UserDAO;
import bg.swift.order.rest.entities.LoginStatus;
import bg.swift.order.rest.entities.User;


/**
 * AddNewTaskServlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Do not use GET to send data to server.");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		HttpSession session = request.getSession();
		
		if (session.isNew()) {
			session.setAttribute("logged_status", LoginStatus.NOT_LOGGED_IN);
		}

		UserDAO userDao = new UserDAO();
		User foundUser = userDao.findUserByUsernameAndPassword(username, password);

		if (foundUser != null) {
			session.setAttribute("logged_status", LoginStatus.LOGGED_IN);
			session.setAttribute("user", foundUser);
			
			response.sendRedirect("index.jsp");
		} else {
			response.sendRedirect("login.jsp?error=1&username=" + username);
		}
	}

}
