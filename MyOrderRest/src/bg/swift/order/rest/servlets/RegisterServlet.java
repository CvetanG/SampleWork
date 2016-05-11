package bg.swift.order.rest.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bg.swift.order.rest.dao.UserDAO;
import bg.swift.order.rest.entities.User;
import bg.swift.order.rest.utils.StringUtils;

/**
 * AddNewTaskServlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("cpassword");

		if (StringUtils.isNotNullOrEmpty(username)
				&& StringUtils.isNotNullOrEmpty(password)
				&& StringUtils.isNotNullOrEmpty(confirmPassword)
				&& password.equals(confirmPassword)) {

			User newUser = new User(username, password);
			UserDAO userDAO = new UserDAO();

			int createdUserId = userDAO.insertNew(newUser);
			if (createdUserId > 0) {
				response.sendRedirect("registerResult.jsp");
			} else {
				response.sendRedirect("registerResult.jsp?error=1");
			}
		} else {
			// TODO should handle errors when passwords do not match or username invalid
		}
	}

}
