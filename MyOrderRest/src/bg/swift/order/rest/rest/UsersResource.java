package bg.swift.order.rest.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import bg.swift.order.rest.dao.OrderDAO;
import bg.swift.order.rest.dao.UserDAO;
import bg.swift.order.rest.entities.Order;
import bg.swift.order.rest.entities.User;

@Path("/users")
public class UsersResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{userId}/")
    public User getById(@PathParam("userId") Integer userId) {

        UserDAO userDAO = new UserDAO();

        User foundUser = userDAO.getById(userId);
        if (foundUser != null) {

            OrderDAO ordersDAO = new OrderDAO();
            List<Order> ordersList = ordersDAO.findOrdersByUser(foundUser, false);
            foundUser.setOrders(ordersList);

            return foundUser;
        }

        return null;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{userId}")
    public User updatePassword(@PathParam("userId") Integer userId, User inUserData) {
        
    	UserDAO userDAO = new UserDAO();

        User foundUser = userDAO.getById(userId);
        if (foundUser != null) {

            if (inUserData != null) {
                foundUser.setPassword(inUserData.getPassword());
               userDAO.update(foundUser);
            }

            return foundUser;
        }

        return null;
    }
}
