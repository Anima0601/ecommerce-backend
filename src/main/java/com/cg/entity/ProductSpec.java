package com.cg.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="product_spec")
public class ProductSpec {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="spec_id")
	private Integer specId;
	@Column(name="spec_name" , nullable = false)
	private String specName;
	@Column(name="spec_value" , nullable = false)
	private String specValue;
	@ManyToOne
	@JoinColumn(name="prod_id", nullable=false)
	private Product product;
	public Integer getSpecId() {
		return specId;
	}
	public void setSpecId(Integer specId) {
		this.specId = specId;
	}
	public String getSpecName() {
		return specName;
	}
	public void setSpecName(String specName) {
		this.specName = specName;
	}
	public String getSpecValue() {
		return specValue;
	}
	public void setSpecValue(String specValue) {
		this.specValue = specValue;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	
	
	

}
