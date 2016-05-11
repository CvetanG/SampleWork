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
import bg.swift.order.rest.utils.DateUtils;
import bg.swift.order.rest.utils.StringUtils;

@WebServlet("/UpdateOrderServlet")
public class UpdateOrderServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Integer id, Status status, User owner, Product product, Integer pcs, Date createdAt, Date updatedAt
	//p.setGender(Gender.valueOf(gender))
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("order_id");
        String status_row = request.getParameter("status");
        Status status = Status.valueOf(status_row);
        String product_row = request.getParameter("product");
        Integer product = Integer.parseInt(product_row);
        String pcs_row = request.getParameter("pcs");
        Integer pcs = Integer.parseInt(pcs_row);
        User loggedUser = (User) request.getSession().getAttribute("user");

        if (StringUtils.isNotNullOrEmpty(orderId)
                && StringUtils.isNotNullOrEmpty(status_row)
                && StringUtils.isNotNullOrEmpty(product_row)
                && StringUtils.isNotNullOrEmpty(pcs_row)
                && loggedUser != null) {
        	
        	ProductDAO productDAO = new ProductDAO();
            Product foundProduct = productDAO.getById(product);
            
            OrderDAO ordersDAO = new OrderDAO();
         // Integer id, Status status, User owner, Product product, Integer pcs, Date createdAt, Date updatedAt
            
            Order newOrder = new Order(Integer.valueOf(orderId), status, loggedUser, foundProduct, pcs, null, DateUtils.getCurrentTimeStamp());
            int newOrderId = ordersDAO.update(newOrder);

            if (newOrderId > 0) {
                response.sendRedirect("updateOrderResult.jsp?success=1");
            } else {
                response.sendRedirect("updateOrderResult.jsp?error=1");
            }
        } else {
            response.sendRedirect(String.format("updateOrderResult.jsp?id=%s&error=1", orderId));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Do not use GET to send data to server.");
    }
}
