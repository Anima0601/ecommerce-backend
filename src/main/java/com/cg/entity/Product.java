package com.cg.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="product")
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="prod_id")
	private Integer prodId;
	@Column(name="prod_name" ,nullable=false)
	private String prodName;
	@Column(name="prod_desc")
	private String prodDesc;
	@Column(name="price" , nullable =false)
	private Double price;
	@Column(name="stock", nullable=false)
	private int stock;
	@Column(name="prod_image")
	private String prodImage;
    @OneToMany(mappedBy="product",cascade=CascadeType.ALL)
	private List<ProductSpec> spec;
	public Integer getProdId() {
		return prodId;
	}
	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getProdDesc() {
		return prodDesc;
	}
	public void setProdDesc(String prodDesc) {
		this.prodDesc = prodDesc;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getProdImage() {
		return prodImage;
	}
	public void setProdImage(String prodImage) {
		this.prodImage = prodImage;
	}
	public List<ProductSpec> getSpec() {
		return spec;
	}
	public void setSpecs(List<ProductSpec> spec) {
		this.spec = spec;
	}
    
    
}
