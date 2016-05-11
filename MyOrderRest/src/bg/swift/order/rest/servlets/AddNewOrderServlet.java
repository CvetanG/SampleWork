package bg.swift.order.rest.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bg.swift.order.rest.dao.OrderDAO;
import bg.swift.order.rest.dao.ProductDAO;
import bg.swift.order.rest.entities.Order;
import bg.swift.order.rest.entities.Product;
import bg.swift.order.rest.entities.Status;
import bg.swift.order.rest.entities.User;
import bg.swift.order.rest.utils.StringUtils;

@WebServlet("/AddNewOrderServlet")
public class AddNewOrderServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String product_row = request.getParameter("product");
        Integer product = Integer.parseInt(product_row);
        
        String pcs_row = request.getParameter("pcs");
        Integer pcs = Integer.parseInt(pcs_row);
        
        User loggedUser = (User) request.getSession().getAttribute("user");

        if (StringUtils.isNotNullOrEmpty(product_row)
                && StringUtils.isNotNullOrEmpty(pcs_row)
                && loggedUser != null) {
        	
        	ProductDAO productDAO = new ProductDAO();
            Product foundProduct = productDAO.getById(product);

            OrderDAO ordersDAO = new OrderDAO();
            Order newOrder = new Order(null, Status.NEW, loggedUser, foundProduct, pcs, null, null);
            int newOrderId = ordersDAO.insertNew(newOrder);

            if (newOrderId > 0) {
                response.sendRedirect("addOrderResult.jsp?success=1");
            } else {
                response.sendRedirect("addOrderResult.jsp?error=1");
            }
        } else {
            response.sendRedirect("addOrderResult.jsp?error=1");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Do not use GET to send data to server.");
    }
}
