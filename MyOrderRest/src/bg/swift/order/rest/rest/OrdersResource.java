package bg.swift.order.rest.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import bg.swift.order.rest.dao.OrderDAO;
import bg.swift.order.rest.entities.Order;

@Path("/orders")
public class OrdersResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{orderId}/")
    public Order getById(@PathParam("orderId") Integer orderId) {

        OrderDAO orderDAO = new OrderDAO();

        Order foundOrder = orderDAO.getById(orderId);
        if (foundOrder != null) {

            return foundOrder;
        }

        return null;
    }
    
    /*
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{orderId}")
    public User updatePassword(@PathParam("orderId") Integer orderId, User inOrderData) {
        OrderDAO orderDAO = new OrderDAO();

        Order foundOrder = orderDAO.getById(orderId);
        if (foundOrder != null) {

            if (inOrderData != null) {
                foundOrder.setPassword(inOrderData.getPassword());

            }

            return foundOrder;
        }

        return null;
    }
    */
}
