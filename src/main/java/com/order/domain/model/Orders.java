package com.order.domain.model;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Table
public class Orders {

	@Id
	@JsonIgnore
	private int systemId;

	@NotNull
	@Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}", message = "orderId should be UUID format")
	private String orderId;

	@NotNull
	private int status;

	@DecimalMin(value = "0.1", inclusive = true, message = "min value 0.1 or higher")
	@DecimalMax(value = "9999.99", inclusive = true, message = "max value 9999.99 or lower")
	private double orderTotal;

	@DecimalMin(value = "0.1", inclusive = true, message = "min value 0.1 or higher")
	@DecimalMax(value = "9999.99", inclusive = true, message = "max value 9999.99 or lower")
	private double shippingCost;

	@Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}", message = "customerId should be UUID format")
	private String customerId;

	@Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}", message = "itemId should be UUID format")
	private String itemId;

	@DecimalMin(value = "0.1", inclusive = true, message = "min value 0.1 or higher")
	@DecimalMax(value = "9999.99", inclusive = true, message = "max value 9999.99 or lower")
	private int quantity;

	private String note;

	@NotNull
	private Long time;

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public double getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(double orderTotal) {
		this.orderTotal = orderTotal;
	}

	public double getShippingCost() {
		return shippingCost;
	}

	public void setShippingCost(double shippingCost) {
		this.shippingCost = shippingCost;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String string) {
		this.itemId = string;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getSystemId() {
		return systemId;
	}

	public void setSystemId(int systemId) {
		this.systemId = systemId;
	}

}
