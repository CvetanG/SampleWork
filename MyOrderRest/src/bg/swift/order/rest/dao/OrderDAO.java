package bg.swift.order.rest.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import bg.swift.order.rest.dal.DBManager;
import bg.swift.order.rest.dal.TableRow;
import bg.swift.order.rest.entities.Order;
import bg.swift.order.rest.entities.Product;
import bg.swift.order.rest.entities.Status;
import bg.swift.order.rest.entities.User;

public class OrderDAO implements CrudDAO<Order> {

    @Override
    public int insertNew(Order order) {
        try (DBManager dbm = new DBManager(true)) {

            String query = buildInsertQuery(order);

            dbm.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public Order getById(Integer id) {
        Order order = null;
        try (DBManager dbm = new DBManager(true)) {
            String selectQry = String.format("SELECT * FROM orders WHERE id_order='%s'", id);

            TableRow row = dbm.selectOne(selectQry);
            
            UserDAO userDAO = new UserDAO();
            Integer userId = (Integer) row.getValue("user_id");
            User foundUser = userDAO.getById(userId);
            
            ProductDAO productDAO = new ProductDAO();
            Integer productId = (Integer) row.getValue("product_id");
            Product foundProduct = productDAO.getById(productId);
            
            // Integer id, Status status, User owner, Product product, Integer pcs, Date createdAt, Date updatedAt
            
            order = new Order(
            		 (Integer) row.getValue("id_order"),
            		 Status.valueOf((String) row.getValue("status")),
                     foundUser,
                     foundProduct,
                     (Integer) row.getValue("pcs"),
                    (Date) row.getValue("created_at"),
                    (Date) row.getValue("update_at"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return order;
    }

    public List<Order> findOrdersByUser(User user, boolean setOwner) {
        List<Order> fobu = new LinkedList<>();
        try (DBManager dbm = new DBManager(true)) {
            String selectQry = String.format("SELECT * FROM orders WHERE user_id='%s' ORDER BY created_at",
                    user.getId());

            List<TableRow> rows = dbm.selectMany(selectQry);
            
            
         // Integer id, Status status, User owner, Product product, Integer pcs, Date createdAt, Date updatedAt
            
            for (TableRow row : rows) {
            	
            	ProductDAO productDAO = new ProductDAO();
            	Integer productId = (Integer) row.getValue("product_id");
            	Product foundProduct = productDAO.getById(productId);
            	
                Order order = new Order (
                        (Integer) row.getValue("id_order"),
                        Status.valueOf((String) row.getValue("status")),
                        setOwner ? user : null,
                        foundProduct,
                        (Integer) row.getValue("pcs"),
                        (Date) row.getValue("created_at"),
                        (Date) row.getValue("update_at")
                );
                fobu.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return fobu;
    }

    public List<Order> findOrdersByUser(User user) {
        return findOrdersByUser(user, true);
    }

    public Order findOrderByIdAndUser(String orderId, User loggedUser) {

        Order order = null;
        try (DBManager dbm = new DBManager(true)) {
            String selectQry = String.format("SELECT * FROM orders WHERE id_order='%s' AND user_id='%s'",
                    orderId,
                    loggedUser.getId());

            TableRow row = dbm.selectOne(selectQry);
            
            ProductDAO productDAO = new ProductDAO();
        	Integer productId = (Integer) row.getValue("product_id");
        	Product foundProduct = productDAO.getById(productId);
            
         // Integer id, Status status, User owner, Product product, Integer pcs, Date createdAt, Date updatedAt
            
            order = new Order(
                    (Integer) row.getValue("id_order"),
                    Status.valueOf((String) row.getValue("status")),
                    loggedUser,
                    foundProduct,
                    (Integer) row.getValue("pcs"),
                    (Date) row.getValue("created_at"),
                    (Date) row.getValue("update_at"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return order;
    }

    public int update(Order order) {
        try (DBManager dbm = new DBManager(true)) {

            String query = buildUpdateQuery(order);

            return dbm.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public boolean delete(Order order) {
        return false;
    }

    public String buildInsertQuery(Order order) {
        List<String> fields = new LinkedList<>();
        List<String> values = new LinkedList<>();

        if (order.getId() != null) {
            fields.add("id_order");
            values.add(order.getId().toString());
        }
        if (order.getStatus() != null) {
        	fields.add("status");
        	values.add(String.format("%s", order.getStatus()));
        }
        if (order.getOwner() != null) {
            fields.add("user_id");
            values.add(String.format("%s", order.getOwner().getId()));
        }
        if (order.getProduct() != null) {
            fields.add("product_id");
            values.add(String.format("'%s'", order.getProduct()));
        }
        if (order.getPcs() != null) {
            fields.add("pcs");
            values.add(String.format("'%s'", order.getPcs()));
        }

        fields.add("created_at");
        values.add("NOW()");

        return String.format("INSERT INTO orders (%s) VALUES (%s)",
                String.join(", ", fields),
                String.join(", ", values));
    }

    public String buildUpdateQuery(Order order) {
        List<String> setQuery = new LinkedList<>();

        if (order.getStatus() != null) {
            setQuery.add("status='" + order.getStatus() + "'");
        }
        if (order.getOwner() != null) {
            setQuery.add("user_id=" + order.getOwner().getId());
        }
        if (order.getProduct() != null) {
            setQuery.add("product_id='" + order.getProduct() + "'");
        }
        if (order.getPcs() != null) {
        	setQuery.add("pcs='" + order.getPcs() + "'");
        }
        
        
        setQuery.add("updated_at=NOW()");

        return String.format("UPDATE orders SET %s WHERE id=%d",
                String.join(", ", setQuery),
                order.getId());
    }

}
