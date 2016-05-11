package bg.swift.order.rest.entities;

import java.util.Date;

public class Product {
	
	private Integer id;
	private String category;
	private String manufacturer;
	private String product;
	private double price;
	private Date createdAt;
	private Date UpdatedAt;
	
	public Product() {
		
	}
	
	public Product(Integer id, String category, String manufacturer, String product, double price, Date createdAt, Date UpdatedAt) {
		super();
		this.id = id;
		this.category = category;
		this.manufacturer = manufacturer;
		this.product = product;
		this.price = price;
		this.createdAt = createdAt;
	}
	
	public Date getUpdatedAt() {
		return UpdatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		UpdatedAt = updatedAt;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", category=" + category + ", manufacturer=" + manufacturer + ", product="
				+ product + ", price=" + price + ", createdAt=" + createdAt + ", UpdatedAt=" + UpdatedAt + "]";
	}
	
}
