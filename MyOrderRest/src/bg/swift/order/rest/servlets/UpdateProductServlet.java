package bg.swift.order.rest.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bg.swift.order.rest.dao.ProductDAO;
import bg.swift.order.rest.entities.Product;
import bg.swift.order.rest.entities.User;
import bg.swift.order.rest.utils.DateUtils;
import bg.swift.order.rest.utils.StringUtils;

@WebServlet("/UpdateProductServlet")
public class UpdateProductServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productId = request.getParameter("product_id");
		String category = request.getParameter("category");
        String manufacturer = request.getParameter("manufacturer");
        String product = request.getParameter("product");
        String price_row = request.getParameter("price");
        Double price= Double.parseDouble(price_row);
		User loggedUser = (User) request.getSession().getAttribute("user");
		
		if (StringUtils.isNotNullOrEmpty(category)
                && StringUtils.isNotNullOrEmpty(manufacturer)
                && StringUtils.isNotNullOrEmpty(product)
                && StringUtils.isNotNullOrEmpty(price_row)
                && loggedUser != null) {

            ProductDAO productsDAO = new ProductDAO();
            Product newProduct = new Product(Integer.valueOf(productId), category, manufacturer, product, price, null, DateUtils.getCurrentTimeStamp());
            int newProductId = productsDAO.update(newProduct);

            if (newProductId > 0) {
                response.sendRedirect("updateProductResult.jsp?success=1");
            } else {
                response.sendRedirect("updateProductResult.jsp?error=1");
            }
        } else {
            response.sendRedirect(String.format("updateProductResult.jsp?id=%s&error=1", productId));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Do not use GET to send data to server.");
    }
}
