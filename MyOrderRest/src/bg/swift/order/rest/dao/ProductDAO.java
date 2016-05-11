package bg.swift.order.rest.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import bg.swift.order.rest.dal.DBManager;
import bg.swift.order.rest.dal.TableRow;
import bg.swift.order.rest.entities.Product;


public class ProductDAO implements CrudDAO<Product> {

    @Override
    public Product getById(Integer id) {

        Product returnProduct = null;

        try (DBManager dbm = new DBManager(true)) {

            TableRow row = dbm.selectOne("SELECT * FROM products WHERE id_product=" + id);
            
            returnProduct = new Product((Integer) row.getValue("id_product"),
                    row.getValue("category").toString(),
                    row.getValue("manufacturer").toString(),
                    row.getValue("product").toString(),
                    ((BigDecimal) row.getValue("price")).doubleValue(),
                    (Date) row.getValue("created_at"),
                    (Date) row.getValue("updated_at")
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return returnProduct;
    }

    @Override
    public int insertNew(Product product) {
        try (DBManager dbm = new DBManager(true)) {

            String query = buildInsertQuery(product);

            dbm.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int update(Product product) {
    	try (DBManager dbm = new DBManager(true)) {

            String query = buildUpdateQuery(product);

            dbm.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public boolean delete(Product product) {
    	
    	try (DBManager dbm = new DBManager(true)) {
    		
    		Integer id = product.getId();
    		String query ="DELETE FROM products WHERE id_product=" + id;
    		dbm.executeQuery(query); 
            System.out.println("Deleted product with id:" + id);
    	} catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public String buildInsertQuery(Product product) {
        List<String> fields = new LinkedList<>();
        List<String> values = new LinkedList<>();

        if (product.getId() != null) {
            fields.add("id_product");
            values.add(product.getId().toString());
        }
        if (product.getCategory() != null) {
            fields.add("category");
            values.add(String.format("'%s'", product.getCategory()));
        }
        if (product.getManufacturer() != null) {
            fields.add("manufacturer");
            values.add(String.format("'%s'", product.getManufacturer()));
        }
        if (product.getProduct() != null) {
        	fields.add("product");
        	values.add(String.format("'%s'", product.getProduct()));
        }
        if (product.getPrice() != 0) {
        	fields.add("price");
        	values.add(String.format("'%s'", product.getPrice()));
        }

        fields.add("created_at");
        values.add("NOW()");

        return String.format("INSERT INTO products (%s) VALUES (%s)",
                String.join(", ", fields),
                String.join(", ", values));
    }
    
    public String buildUpdateQuery(Product product) {
        List<String> setQuery = new LinkedList<>();
        
        if (product.getCategory() != null) {
        	setQuery.add("category='" + product.getCategory() + "'");
        }
        if (product.getManufacturer() != null) {
        	setQuery.add("manufacturer='" + product.getManufacturer() + "'");
        }
        if (product.getProduct() != null) {
        	setQuery.add("product='" + product.getProduct() + "'");
        }
        if (product.getPrice() != 0) {
        	setQuery.add("price='" + product.getPrice() + "'");
        }
        setQuery.add("updated_at=NOW()");

        return String.format("UPDATE products SET %s WHERE id_product=%d",
                String.join(", ", setQuery),
                product.getId());
    }
}
