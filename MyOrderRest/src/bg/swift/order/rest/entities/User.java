package bg.swift.order.rest.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bg.swift.order.rest.dao.OrderDAO;

public class User {

	private Integer id;
	private String username;
	private String password;
	
	private List<Order> orders = new ArrayList<>();
 	
	private Date createdAt;
	private Date updatedAt;

	public User() {
	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public User(int id, String username, String password, List<Order> orders, Date createdAt, Date updatedAt) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.orders = orders;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public void updateOrders() {
		OrderDAO ordersDAO = new OrderDAO();
		List<Order> ordersList = ordersDAO.findOrdersByUser(this);
		this.setOrders(ordersList);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", orders=" + orders
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
	
}
