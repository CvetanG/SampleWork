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
import bg.swift.order.rest.utils.StringUtils;

@WebServlet("/AddNewProductServlet")
public class AddNewProductServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
        String category = request.getParameter("category");
        String manifactorer = request.getParameter("manufacturer");
        String product = request.getParameter("product");
        String price_row = request.getParameter("price");
        Double price= Double.parseDouble(price_row);
        User loggedUser = (User) request.getSession().getAttribute("user");
        
       

        if (StringUtils.isNotNullOrEmpty(category)
                && StringUtils.isNotNullOrEmpty(manifactorer)
                && StringUtils.isNotNullOrEmpty(product)
                && StringUtils.isNotNullOrEmpty(price_row)
                && loggedUser != null) {

            ProductDAO productsDAO = new ProductDAO();
            Product newProduct = new Product(null, category, manifactorer, product, price, null, null);
            int newProductId = productsDAO.insertNew(newProduct);

            if (newProductId > 0) {
                response.sendRedirect("addProductResult.jsp?success=1");
            } else {
                response.sendRedirect("addProductResult.jsp?error=1");
            }
        } else {
            response.sendRedirect("addProductResult.jsp?error=1");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Do not use GET to send data to server.");
        
       
    
    }
}
