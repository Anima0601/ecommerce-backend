package com.cg.dto;

public class OrderDto {

    private Integer orderId;
    private double orderAmt;
    private String orderStatus;
    private String prodName;
    private int qty;
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public double getOrderAmt() {
		return orderAmt;
	}
	public void setOrderAmt(double orderAmt) {
		this.orderAmt = orderAmt;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}

    
}