package bg.swift.order.rest.entities;

import java.util.Date;

public class Order {
	private Integer id;
	private Status status;
	private Product product;
	private Integer pcs;

	private User owner;
	
	private Date createdAt;
	private Date updatedAt;
	
	public Order() {
		
	}
	
	public Order(Integer id, Status status, User owner, Product product, Integer pcs, Date createdAt,
			Date updatedAt) {
		this.id = id;
		this.status = status;
		this.product = product;
		this.pcs = pcs;
		this.owner = owner;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public Integer getPcs() {
		return pcs;
	}
	public void setPcs(Integer pcs) {
		this.pcs = pcs;
	}
	
	public User getOwner() {
		return owner;
	}
	
	public void setOwner(User owner) {
		this.owner = owner;
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

	@Override
	public String toString() {
		return "Order [id=" + id + ", status=" + status + ", product=" + product + ", pcs=" + pcs + ", owner=" + owner
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
	
	
}
