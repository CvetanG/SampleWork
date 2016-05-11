package bg.swift.order.rest.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import bg.swift.order.rest.dao.ProductDAO;
import bg.swift.order.rest.entities.Product;

@Path("/products")
public class ProductsResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{productId}/")
    public Product getById(@PathParam("productId") Integer productId) {

        ProductDAO productDAO = new ProductDAO();

        Product foundProduct = productDAO.getById(productId);
        if (foundProduct != null) {

            return foundProduct;
        }

        return null;
    }
    
    @DELETE
    @Path("/{productId}/")
    public void deleteProduct(@PathParam("productId") Integer productId) {
    	
    	ProductDAO productDAO = new ProductDAO();
    	
    	Product foundProduct = productDAO.getById(productId);
    	if (foundProduct != null) {
    		
    		productDAO.delete(foundProduct);
    	}
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{productId}")
    public Product updateProduct(@PathParam("productId") Integer productId, Product inProductData) {
        ProductDAO productDAO = new ProductDAO();

        Product foundProduct = productDAO.getById(productId);
        if (foundProduct != null) {

            if (inProductData != null) {
                foundProduct.setCategory(inProductData.getCategory());
                foundProduct.setManufacturer(inProductData.getManufacturer());
                foundProduct.setProduct(inProductData.getProduct());
                foundProduct.setPrice(inProductData.getPrice());
                
                productDAO.update(foundProduct);
            }

            return foundProduct;
        }

        return null;
    }
}
