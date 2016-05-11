package bg.swift.order.rest;

import java.sql.SQLException;

import bg.swift.order.rest.dao.UserDAO;
import bg.swift.order.rest.entities.User;

public class Main {
	
	public static void main(String[] args) throws SQLException {
/*
		DBManager dbm = new DBManager();
		dbm.open();
		TableRow r = dbm.selectOne("select * from users where id=1");
		String query = "INSERT INTO `todolist`.`users` (`username`, `password`,	`created_at`, `updated_at`)	VALUES ( 'anatoli', '1234', NOW(), NOW())";
		dbm.executeQuery(query);
		List<TableRow> lr = dbm.selectMany("select * from users");
*/

		UserDAO userDAO = new UserDAO();
		User a = userDAO.getById(5);

//		dbm.close();
	}
	

}
